package be.gerard.ubrew.core.service.decoder;

import be.gerard.common.decoder.Decoder;
import be.gerard.ubrew.core.interface_v1.model.product.BeerType;
import be.gerard.ubrew.core.service.dao.BeerTypeDAO;
import be.gerard.ubrew.core.service.model.product.BeerTypeRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * BeerTypeDecoder
 *
 * @author Bart Gerard
 */
@Component
public class BeerTypeDecoder implements Decoder<BeerTypeRecord, BeerType> {

    @Autowired
    private BeerTypeDAO beerTypeDAO;

    @Override
    public BeerTypeRecord decode(BeerType beerType) {
        BeerTypeRecord beerTypeRecord = beerType.getId() != null ? beerTypeDAO.find(beerType.getId()) : null;
        
        if (beerTypeRecord == null) {
            beerTypeRecord = new BeerTypeRecord();
        }

        beerTypeRecord.setLabelKey(beerType.getLabelKey());
        beerTypeRecord.setDescription(beerType.getDescription());
        beerTypeRecord.setAlcoholMin(beerType.getAlcoholMin());
        beerTypeRecord.setAlcoholMax(beerType.getAlcoholMax());
        beerTypeRecord.setColorMin(beerType.getColorMin());
        beerTypeRecord.setColorMax(beerType.getColorMax());
        beerTypeRecord.setBitterMin(beerType.getBitterMin());
        beerTypeRecord.setBitterMax(beerType.getBitterMax());

        return beerTypeRecord;
    }

    @Override
    public BeerType encode(BeerTypeRecord beerTypeRecord) {
        BeerType beerType = new BeerType();
        beerType.setId(beerTypeRecord.getId());
        beerType.setLabelKey(beerTypeRecord.getLabelKey());
        beerType.setDescription(beerTypeRecord.getDescription());
        beerType.setAlcoholMin(beerTypeRecord.getAlcoholMin());
        beerType.setAlcoholMax(beerTypeRecord.getAlcoholMax());
        beerType.setColorMin(beerTypeRecord.getColorMin());
        beerType.setColorMax(beerTypeRecord.getColorMax());
        beerType.setBitterMin(beerTypeRecord.getBitterMin());
        beerType.setBitterMax(beerTypeRecord.getBitterMax());
        return beerType;
    }

}