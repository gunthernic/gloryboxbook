package fr.gunther.glorybox.website.service.impl;

import fr.gunther.glorybox.website.dto.CountryDeliveryDTO;
import fr.gunther.glorybox.website.entity.CountryDelivery;
import fr.gunther.glorybox.website.repository.CountryDeliveryRepository;
import fr.gunther.glorybox.website.service.CountryDeliveryService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryDeliveryServiceImpl implements CountryDeliveryService {

        @Autowired
        private CountryDeliveryRepository countryDeliveryRepository;

        @Override
        public List<CountryDeliveryDTO> getAllCountryDelivery() {
                return countryDeliveryRepository.findAll().stream().map(x -> x.toDto()).collect(
                        Collectors.toList());
        }

        @Override
        public String findById(Long id) {
                return countryDeliveryRepository.findOne(id).getCountry();
        }

        @Override
        public Float getPriceByCountryId(Long id) {
                return countryDeliveryRepository.findOne(id).getPrice();
        }

        @Override
        public void updatePrice(Float price, Long idcountry) {
                CountryDelivery countryDelivery = countryDeliveryRepository.findOne(idcountry);
                countryDelivery.setPrice(price);
                countryDeliveryRepository.save(countryDelivery);
        }
}
