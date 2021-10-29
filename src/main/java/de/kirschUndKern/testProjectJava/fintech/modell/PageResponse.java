package de.kirschUndKern.testProjectJava.fintech.modell;

import java.util.List;

public class PageResponse<T> {
  private final Integer pageNumber;
  private final Integer resultsPerPage;
  private final Integer totalResults;
  private final Double totalPages;
  private final List<T> items;

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
    this.totalPages = calTotalPages();
  }

  private Double calTotalPages() {
    return Math.ceil(Double.valueOf(totalResults) / (Double.valueOf(resultsPerPage)));
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

  public Double getTotalPages(){
    return totalPages;
  }

  //TO-DO: Methode for calculatin the total number of pages

}
