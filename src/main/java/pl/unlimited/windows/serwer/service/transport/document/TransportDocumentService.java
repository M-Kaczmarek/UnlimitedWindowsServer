package pl.unlimited.windows.serwer.service.transport.document;

import org.springframework.stereotype.Service;
import pl.unlimited.windows.serwer.model.TransportDocument;
import pl.unlimited.windows.serwer.model.dto.TransportDocumentDto;
import pl.unlimited.windows.serwer.repository.TransportDocumentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportDocumentService {

    private final TransportDocumentRepository transportDocumentRepository;

    public TransportDocumentService(TransportDocumentRepository transportDocumentRepository) {
        this.transportDocumentRepository = transportDocumentRepository;
    }

    public List<TransportDocumentDto> getAllTransportDocuments(){
     return transportDocumentRepository.findAll().stream()
             .map(TransportDocument::toDto)
             .collect(Collectors.toList());
    }
}
