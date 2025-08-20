class Carrier {
    private String name;
    private Contact contactInfo;
    private Rates rates;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Contact getContactInfo() { return contactInfo; }
    public void setContactInfo(Contact contactInfo) { this.contactInfo = contactInfo; }
    public Rates getRates() { return rates; }
    public void setRates(Rates rates) { this.rates = rates; }

    public Rates getShippingRates() { return rates; }
}