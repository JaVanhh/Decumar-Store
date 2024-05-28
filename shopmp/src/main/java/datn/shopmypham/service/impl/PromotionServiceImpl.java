package datn.shopmypham.service.impl;

import datn.shopmypham.entity.Promotion;
import datn.shopmypham.responsitory.PromotionRepository;
import datn.shopmypham.service.PromotionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;

    public PromotionServiceImpl(PromotionRepository promotionRepository) {
        this.promotionRepository = promotionRepository;
    }

    @Override
    public List<Promotion> getAllPromotion(String keyword, int page, int size) throws Exception {
        return promotionRepository.getAllPromotion(keyword, page, size);
    }

    @Override
    public void addPromotion(Promotion promotion) throws Exception {
        promotionRepository.addPromotion(promotion);
    }

    @Override
    public void deletePromotion(Integer promotionId) throws Exception {
        promotionRepository.deletePromotion(promotionId);
    }

    @Override
    public void updatePromotion(Promotion promotion, Integer promotionId) throws Exception {
        promotionRepository.updatePromotion(promotion, promotionId);
    }
}
