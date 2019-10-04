package resources;

public enum ProductTypeTabEnum {
    WOMEN ( "WOMEN" ), DRESSES ( "DRESSES" ), TSHIRT ( "T-SHIRT" );
    private String label;

    ProductTypeTabEnum ( String label ) {
        this.label = label;
    }

    public String getLabel ( ) {
        return label;
    }

}
