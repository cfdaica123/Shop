package com.diorama.shop.dto.client.response;

public class AddressResponseDTO {
    private Long id;
    private String recipientName;
    private String phoneNumber;
    private String street;
    private String ward;
    private String district;
    private String city;
    private boolean isDefault;

    // Default constructor
    public AddressResponseDTO() {}

    // Constructor with all fields
    public AddressResponseDTO(Long id, String recipientName, String phoneNumber, String street, 
                            String ward, String district, String city, boolean isDefault) {
        this.id = id;
        this.recipientName = recipientName;
        this.phoneNumber = phoneNumber;
        this.street = street;
        this.ward = ward;
        this.district = district;
        this.city = city;
        this.isDefault = isDefault;
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the recipientName
     */
    public String getRecipientName() {
        return recipientName;
    }

    /**
     * @param recipientName the recipientName to set
     */
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    /**
     * @return String return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return String return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * @param street the street to set
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return String return the ward
     */
    public String getWard() {
        return ward;
    }

    /**
     * @param ward the ward to set
     */
    public void setWard(String ward) {
        this.ward = ward;
    }

    /**
     * @return String return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return String return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return boolean return the isDefault
     */
    public boolean isIsDefault() {
        return isDefault;
    }

    /**
     * @param isDefault the isDefault to set
     */
    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

}
