package pl.unlimited.windows.serwer.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;


@JsonDeserialize(builder = ReceiverDto.ReceiverDtoBuilder.class)
public class ReceiverDto {
    private final String name;
    private final String address;
    private final String city;
    private final String email;
    private final String phoneNumber;
    private final String postalCode;

    public ReceiverDto(ReceiverDtoBuilder builder) {
        this.name = builder.name;
        this.address = builder.address;
        this.city = builder.city;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.postalCode = builder.postalCode;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    @JsonPOJOBuilder(withPrefix = "", buildMethodName = "build")
    public static class ReceiverDtoBuilder {
        private String name;
        private String address;
        private String city;
        private String email;
        private String phoneNumber;
        private String postalCode;

        public ReceiverDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ReceiverDtoBuilder address(String address) {
            this.address = address;
            return this;
        }

        public ReceiverDtoBuilder city(String city) {
            this.city = city;
            return this;
        }

        public ReceiverDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public ReceiverDtoBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public ReceiverDtoBuilder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public ReceiverDto build() {
            return new ReceiverDto(this);
        }
    }
}
