package nchandi.spring.services.NCHANDIWebsite_Service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import nchandi.spring.services.NCHANDIWebsite_Service.domain.ResourceItem;
import java.util.List;


public interface ResourceItemRepository extends JpaRepository<ResourceItem, String> {

  List<ResourceItem> findByTypeAndMonthOfYear(String type, String monthOfYear);
}
