package de.kirschUndKern.testProjectJava.fintech.modell;

import java.util.List;

public class PageResponse<T> {
  private final Integer pageNumber;
  private final Integer resultsPerPage;
  private final Integer totalResults;
  private List<T> items;

  public PageResponse(
    Integer pageNumber,
    Integer resultsPerPage,
    Integer totalResults,
    List<T> items
  ){
    this.pageNumber = pageNumber;
    this.resultsPerPage = resultsPerPage;
    this.totalResults = totalResults;
    this.items = items;
  }

  public Integer getPageNumber() {
    return pageNumber;
  }

  public Integer getResultsPerPage() {
    return resultsPerPage;
  }

  public Integer getTotalResults() {
    return totalResults;
  }

  public List<T> getItems() {
    return items;
  }

  public void setItems(List<T> items) {
    this.items = items;
  }

  //TO-DO: Methode for calculatin the total number of pages

}
