package pl.unlimited.windows.serwer.service.send.courier.integration;

import org.springframework.web.client.RestTemplate;
import pl.unlimited.windows.serwer.couriers.api.CourierResponse;
import pl.unlimited.windows.serwer.couriers.api.dpd.*;
import pl.unlimited.windows.serwer.couriers.api.dpd.Package;
import pl.unlimited.windows.serwer.model.TransportDocument;
import pl.unlimited.windows.serwer.model.dto.PackageDataDto;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Base64;

public class DPDCourierStrategy implements AbstractCourierStrategy{

    private static final String DPD_GENERATE_PACKAGE_ADDRESS = "https://dpdservicesdemo.dpd.com.pl/DPDPackageObjServicesService/DPDPackageObjServices";
    private static final String GENERATE_PACKAGE_NUMBER_DPD = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:dpd=\"http://dpdservices.dpd.com.pl/\"><soapenv:Header/><soapenv:Body>%s</soapenv:Body></soapenv:Envelope>";
    private static final String GENERATE_LABEL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:dpd=\"http://dpdservices.dpd.com.pl/\"><soapenv:Header/><soapenv:Body><dpd:generateSpedLabelsV4><dpdServicesParamsV1><policy>STOP_ON_FIRST_ERROR</policy><session><packages><parcels><waybill>%s</waybill></parcels></packages><sessionType>DOMESTIC</sessionType></session></dpdServicesParamsV1><outputDocFormatV1>PDF</outputDocFormatV1><outputDocPageFormatV1>LBL_PRINTER</outputDocPageFormatV1><outputLabelType>BIC3</outputLabelType><labelVariant/><authDataV1><login>test</login><masterFid>1495</masterFid><password>thetu4Ee</password></authDataV1></dpd:generateSpedLabelsV4></soapenv:Body></soapenv:Envelope>";

    @Override
    public  CourierResponse generatePackageNumber(PackageDataDto packageData) {
        var senderData = packageData.getSenderDto();
        var receiverData = packageData.getReceiverDto();
        final var authData =   AuthData.builder()
                .login("test")
                .masterFid("1495")
                .password("thetu4Ee")
                .build();

        var req =  GeneratePackageNumberDPD.builder()
                .generateData(GenerateRequest.builder()
                        .packages(Package.builder()
                                .parcels(Parcel.builder()
                                        .reference("Okno numer: " + packageData.getDocumentNumber())
                                        .weight("20")
                                        .sizeX("4")
                                        .sizeY("5")
                                        .sizeZ("5")
                                        .build())
                                .sender(Sender.builder()
                                        .address(senderData.getAddress())
                                        .city(senderData.getCity())
                                        .countryCode("PL")
                                        .email(senderData.getEmail())
                                        .name(senderData.getName())
                                        .phone(senderData.getPhoneNumber())
                                        .postalCode(senderData.getPostalCode().replace("-", ""))
                                        .build())
                                .receiver(Receiver.builder()
                                        .address(receiverData.getAddress())
                                        .city(receiverData.getCity())
                                        .countryCode("PL")
                                        .email(receiverData.getEmail())
                                        .name(receiverData.getName())
                                        .phone(receiverData.getPhoneNumber())
                                        .postalCode(receiverData.getPostalCode().replace("-", ""))
                                        .build())
                                .payerType("THIRD_PARTY")
                                .thirdPartyFID("1495")
                                .services(pl.unlimited.windows.serwer.couriers.api.dpd.Service.builder()
                                        .declaredValue(DeclaredValue.builder()
                                                .amount("23.30")
                                                .currency("PLN")
                                                .build())
                                        .build())
                                .build())
                        .build())
                .pkgNumsGenerationPolicyV1("ALL_OR_NOTHING")
                .langCode("PL")
                .authData(authData)
                .build();

        RestTemplate restTemplate = new RestTemplate();
       var result = restTemplate.postForObject(DPD_GENERATE_PACKAGE_ADDRESS, String.format(GENERATE_PACKAGE_NUMBER_DPD, toXML(req)), String.class);

       var objResult =  toDPDCourierResponse(result);

       return objResult;
    }

    @Override
    public byte[] generateLabel(final TransportDocument transportDocument) {
       var request = String.format(GENERATE_LABEL, transportDocument.getGeneratedPackageNumber());

        RestTemplate restTemplate = new RestTemplate();
        var result = restTemplate.postForObject(DPD_GENERATE_PACKAGE_ADDRESS, request, String.class);

        result = result.replaceAll("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:generateSpedLabelsV4Response xmlns:ns2=\"http://dpdservices.dpd.com.pl/\"><return><documentData>", "");
        result = result.substring(0, result.indexOf("</documentData>"));
        System.out.println(result);
//
        return Base64.getDecoder().decode(result);

    }

    public CourierResponse toDPDCourierResponse(String response){
       response = response.replace("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">", "");
        response = response.replace("<soap:Body>", "");
        response = response.replace("</soap:Body>", "");
        response = response.replace("</soap:Envelope>", "");
        response = response.replace(" xmlns:ns2=\"http://dpdservices.dpd.com.pl/\"", "");
        response = response.replaceAll("ns2:", "");
        CourierResponse result = null;
        try {
            StringReader sr = new StringReader(response);
            JAXBContext jaxbContext = JAXBContext.newInstance(DPDCourierResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            result =  (DPDCourierResponse) unmarshaller.unmarshal(sr);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
    public String toXML(GeneratePackageNumberDPD req){
        String result = "";

        try {
            //Create JAXB Context
            JAXBContext jaxbContext = JAXBContext.newInstance(GeneratePackageNumberDPD.class);

            //Create Marshaller
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //Required formatting??
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            //Print XML String to Console
            StringWriter sw = new StringWriter();

            //Write XML to StringWriter
            Object employee;
            jaxbMarshaller.marshal(req, sw);

            //Verify XML Content
            result = sw.toString();

            result.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");

        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return result.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>", "");
    }

}
