package datn.shopmypham.responsitory;


import datn.shopmypham.entity.FeedBack;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedBackRepository {

    public List<FeedBack> getAllFeedBack(String keyword, int page, int size) throws Exception;

    public void addFeedBack(FeedBack feedBack) throws Exception;

    public void deleteFeedBack(Integer feedBackId) throws Exception;

    public void updateFeedBack(FeedBack feedBack, Integer feedBackId) throws Exception;
}
