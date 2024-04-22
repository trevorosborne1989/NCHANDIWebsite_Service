package nchandi.spring.services.NCHANDIWebsite_Service.utils;

import java.util.Comparator;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.Facility;

public class FacilityComparator implements Comparator<Facility> {

  @Override
  public int compare(Facility a, Facility b) {
    return a.getName().compareTo(b.getName());
  }
}
