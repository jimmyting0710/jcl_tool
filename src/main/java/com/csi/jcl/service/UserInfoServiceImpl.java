package com.csi.jcl.service;

import com.csi.jcl.dao.UserInfoRepository;
import com.csi.jcl.entity.UserInfoEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Optional;

/**
 * 實作UserInfoService功能
 *
 * @author si1206 Sam Chen
 * @version 1.8
 * @date 2021/08/04
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger logger =
            LogManager.getLogger(UserInfoServiceImpl.class);

    /**
     * 注入UserInfoRepository
     *
     * @author si1206 Sam Chen
     * @date 2021/08/17
     */
    private final UserInfoRepository userInfoRepository;

    public UserInfoServiceImpl(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * 依userid尋找對應的UserInfoEntity
     *
     * @param userId userid
     * @return userInfoEntity
     * @author si1206 Sam Chen
     * @date 2021/08/17
     */
    @Override
    public UserInfoEntity findById(String userId) {
        Optional<UserInfoEntity> result = userInfoRepository.findById(userId);
        UserInfoEntity userInfoEntity = null;
        if (result.isPresent()) {
            userInfoEntity = result.get();
            logger.debug(userInfoEntity);
        } else {
            logger.error("Did not find userid" + userId);
            throw new RuntimeException("Did not find userid" + userId);
        }
        return userInfoEntity;
    }

    /**
     * 更新UserInfoEntity的Lastlogindatetime
     *
     * @param userInfoEntity UserInfoEntity
     * @author si1206 Sam Chen
     * @date 2021/08/17
     */
    @Override
    public void updateInfo(UserInfoEntity userInfoEntity) {
        // 將時間依照格式轉換為字串
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
        // 更新userInfoEntity
        userInfoEntity.setLastlogindatetime(timeStamp);
        logger.debug(userInfoEntity);
        userInfoRepository.save(userInfoEntity);
    }
}
