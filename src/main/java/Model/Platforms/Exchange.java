package Model.Platforms;

public class Exchange {

    protected String name;
    protected Boolean allowDemo;
    protected Boolean requireKYC;

//#################### SETTERS AND GETTERS ############################################
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAllowDemo() {
        return allowDemo;
    }

    public void setAllowDemo(Boolean allowDemo) {
        this.allowDemo = allowDemo;
    }

    public Boolean getRequireKYC() {
        return requireKYC;
    }

    public void setRequireKYC(Boolean requireKYC) {
        this.requireKYC = requireKYC;
    }

//#################### CONSTRUCTORS ############################################

    public Exchange() {
        this.name = "Unknown name";
        this.allowDemo = false;
        this.requireKYC = false;
    }
    public Exchange(String name, Boolean allowDemo, Boolean requireKYC) {
        this.name = name;
        this.allowDemo = allowDemo;
        this.requireKYC = requireKYC;
    }



}
