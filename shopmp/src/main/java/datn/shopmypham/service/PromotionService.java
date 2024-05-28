package datn.shopmypham.service;

import datn.shopmypham.entity.Promotion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionService {

    public List<Promotion> getAllPromotion(String keyword, int page, int size) throws Exception;

    public void addPromotion(Promotion promotion) throws Exception;

    public void deletePromotion(Integer promotionId) throws Exception;

    public void updatePromotion(Promotion promotion, Integer promotionId) throws Exception;
}
