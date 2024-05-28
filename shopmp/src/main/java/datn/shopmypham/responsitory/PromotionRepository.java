package datn.shopmypham.responsitory;


import datn.shopmypham.entity.Promotion;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository {

    public List<Promotion> getAllPromotion(String keyword, int page, int size) throws Exception;

    public void addPromotion(Promotion promotion) throws Exception;

    public void deletePromotion(Integer promotionId) throws Exception;

    public void updatePromotion(Promotion promotion, Integer promotionId) throws Exception;

}
