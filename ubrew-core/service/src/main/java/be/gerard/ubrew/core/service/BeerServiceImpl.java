package be.gerard.ubrew.core.service;

import be.gerard.ubrew.core.interface_v1.BeerService;
import be.gerard.ubrew.core.interface_v1.model.product.Beer;
import be.gerard.ubrew.core.interface_v1.model.product.BeerType;
import be.gerard.ubrew.core.service.dao.BeerTypeDAO;
import be.gerard.ubrew.core.service.decoder.BeerTypeDecoder;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BeerServiceImpl
 * 
 * @author bartgerard
 */
@Service
public class BeerServiceImpl implements BeerService {
    
    @Autowired
    private BeerTypeDAO beerTypeDAO;
    
    @Autowired
    private BeerTypeDecoder beerTypeDecoder;

    @Override
    public Beer saveOrUpdate(final Beer beer) {
        return null;
    }

    @Override
    public BeerType saveOrUpdateBeerType(final BeerType beerType) {
        return beerTypeDecoder.encode(beerTypeDAO.saveOrUpdate(beerTypeDecoder.decode(beerType)));
    }
    
    @Override
    public List<BeerType> findAllBeerTypes() {
        return beerTypeDecoder.encode(beerTypeDAO.findAll());
    }
    
}