package datn.shopmypham.service;

import datn.shopmypham.entity.FeedBack;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedBackService {

    public List<FeedBack> getAllFeedBack(String keyword, int page, int size) throws Exception;

    public void addFeedBack(FeedBack feedBack) throws Exception;

    public void deleteFeedBack(Integer feedBackId) throws Exception;

    public void updateFeedBack(FeedBack feedBack, Integer feedBackId) throws Exception;
}
