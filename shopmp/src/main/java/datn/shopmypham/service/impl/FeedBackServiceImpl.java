package datn.shopmypham.service.impl;

import datn.shopmypham.entity.FeedBack;
import datn.shopmypham.responsitory.FeedBackRepository;
import datn.shopmypham.service.FeedBackService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedBackRepository feedBackRepository;

    public FeedBackServiceImpl(FeedBackRepository feedBackRepository) {
        this.feedBackRepository = feedBackRepository;
    }

    @Override
    public List<FeedBack> getAllFeedBack(String keyword, int page, int size) throws Exception {
        return feedBackRepository.getAllFeedBack(keyword, page, size);
    }

    @Override
    public void addFeedBack(FeedBack feedBack) throws Exception {
        feedBackRepository.addFeedBack(feedBack);
    }

    @Override
    public void deleteFeedBack(Integer feedBackId) throws Exception {
        feedBackRepository.deleteFeedBack(feedBackId);
    }

    @Override
    public void updateFeedBack(FeedBack feedBack, Integer feedBackId) throws Exception {
        feedBackRepository.updateFeedBack(feedBack, feedBackId);
    }
}
