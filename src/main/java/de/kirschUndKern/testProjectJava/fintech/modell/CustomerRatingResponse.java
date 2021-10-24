package de.kirschUndKern.testProjectJava.fintech.modell;

public class CustomerRatingResponse {
  private final String firstname;
  private final String secondname;
  private final String salutation;
  private final Integer rating;

  public CustomerRatingResponse(
    String firstname, 
    String secondname, 
    String salutation, 
    Integer rating) {
      this.firstname = firstname;
      this.secondname = secondname;
      this.salutation = salutation;
      this.rating = rating;
  }
  
  public String getFirstname() {
    return firstname;
  }
  public String getSecondname() {
    return secondname;
  }
  public String getSalutation() {
    return salutation;
  }
  public Integer getRating() {
    return rating;
  }
}
