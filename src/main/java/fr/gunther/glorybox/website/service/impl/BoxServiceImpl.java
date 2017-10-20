package fr.gunther.glorybox.website.service.impl;

import fr.gunther.glorybox.website.dto.BoxDTO;
import fr.gunther.glorybox.website.entity.Box;
import fr.gunther.glorybox.website.repository.BoxRepository;
import fr.gunther.glorybox.website.service.BoxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoxServiceImpl implements BoxService {

        @Autowired
        private BoxRepository boxRepository;

        @Override
        public boolean updatePrice(String price) {
                Box box = boxRepository.findFirstByOrderByCreationDateDesc();
                box.setPrice(Float.parseFloat(price));
                boxRepository.save(box);
                return true;
        }

        @Override
        public Float getPriceBox() {
                Box box = boxRepository.findFirstByOrderByCreationDateDesc();
                return box.getPrice();
        }

        @Override
        public List<BoxDTO> getAvailableBoxes() {
                return boxRepository.findAll().stream().map(x -> x.toDto()).collect(Collectors.toList());
        }
}
