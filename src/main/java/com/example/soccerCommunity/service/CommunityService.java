package com.example.soccerCommunity.service;

import com.example.soccerCommunity.dto.CommunityDto;
import com.example.soccerCommunity.entity.Community;
import com.example.soccerCommunity.entity.UserInfo;
import com.example.soccerCommunity.repository.CommunityRepository;
import com.example.soccerCommunity.repository.UserInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final UserInfoRepository userInfoRepository;

    public CommunityService(CommunityRepository communityRepository, UserInfoRepository userInfoRepository){

        this.communityRepository = communityRepository;
        this.userInfoRepository = userInfoRepository;
    }

    public Page<CommunityDto> getCommunityPage(int page, int pageSize) {

        PageRequest pageRequest = PageRequest.of(page, pageSize);
        Page<Community> communityPage = communityRepository.findAllByOrderByCreatedAtDesc(pageRequest);

        return communityPage.map(Community::toDto);
    }

    public CommunityDto getCommunity(Long id) {

        Community community = communityRepository.findById(id).orElse(null);

        return Community.toDto(community);
    }

    @Transactional
    public CommunityDto postCommunity(String username, CommunityDto communityDto) {

        UserInfo userInfo = userInfoRepository.findByUser_Username(username);
        String nickname = userInfo.getNickname();

        Community community = CommunityDto.toEntity(nickname, communityDto);
        Community created = communityRepository.save(community);

        return Community.toDto(created);
    }
}
