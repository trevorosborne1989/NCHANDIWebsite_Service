package nchandi.spring.services.NCHANDIWebsite_Service.utils;

import java.util.Comparator;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.People;

public class PersonComparator implements Comparator<People>{

  @Override
  public int compare(People a, People b) {
    return a.getFirstName().compareTo(b.getFirstName());
  }
}
