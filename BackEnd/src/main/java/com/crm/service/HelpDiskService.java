package com.crm.service;

import com.crm.domain.*;
import com.crm.dto.HelpDiskCommentDto;
import com.crm.dto.HelpDiskDetailsDto;
import com.crm.dto.HelpDiskDto;
import com.crm.dto.TicketResolveDto;
import com.crm.repository.CommentsHelpDiskRepository;
import com.crm.repository.HelpDiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HelpDiskService {
    private final HelpDiskRepository helpDiskRepository;
    private final CommentsHelpDiskRepository commentsHelpDiskRepository;

    @Autowired
    public HelpDiskService(HelpDiskRepository helpDiskRepository, CommentsHelpDiskRepository commentsHelpDiskRepository) {
        this.helpDiskRepository = helpDiskRepository;
        this.commentsHelpDiskRepository = commentsHelpDiskRepository;
    }

    public List<HelpDiskDto> getAllByTopic(String topic) {
        return helpDiskRepository.findAllByTopic(topic)
                .stream()
                .map(helpDisk -> {
                    HelpDiskDto helpDiskDto = new HelpDiskDto();
                    helpDiskDto.setId(helpDisk.getId());
                    helpDiskDto.setTopic(helpDisk.getTopic());
                    helpDiskDto.setTitle(helpDisk.getTitle());
                    helpDiskDto.setPublishedBy(helpDisk.getUser().getUserInformation().getFirstName() + " " + helpDisk.getUser().getUserInformation().getLastName());
                    helpDiskDto.setComments(0);
                    return helpDiskDto;
                }).collect(Collectors.toList());
    }

    public void addHelpDisk(HelpDisk helpDisk, User user) {
        helpDisk.setUser(user);
        helpDisk.setCreated(new Date());
        helpDiskRepository.save(helpDisk);
    }

    public HelpDiskDetailsDto getHelpDiskById(long id) {
        HelpDiskDetailsDto helpDiskDetailsDto = new HelpDiskDetailsDto();
        HelpDisk helpDisk = helpDiskRepository.findById(id);
        helpDiskDetailsDto.setTitle(helpDisk.getTitle());
        helpDiskDetailsDto.setContent(helpDisk.getContent());
        helpDiskDetailsDto.setTopic(helpDisk.getTitle());
        helpDiskDetailsDto.setId(helpDisk.getId());
        helpDiskDetailsDto.setCreated(helpDisk.getCreated());
        helpDiskDetailsDto.setEmployeeName(helpDisk.getUser().getUserInformation().getFirstName() + " " + helpDisk.getUser().getUserInformation().getLastName());
        List<CommentsHelpDisk> commentsHelpDisks = commentsHelpDiskRepository.findAllByHelpDiskId(id);
        helpDiskDetailsDto.setCommentsHelpDisks(commentsHelpDisks);
        return helpDiskDetailsDto;

    }

    public void addComment(HelpDiskCommentDto helpDiskCommentDto, String fullName) {
        HelpDisk helpDisk = helpDiskRepository.findById(helpDiskCommentDto.getHelpDiskId());
        CommentsHelpDisk commentsHelpDisk = new CommentsHelpDisk();
        commentsHelpDisk.setContent(helpDiskCommentDto.getContent());
        commentsHelpDisk.setCreated(new Date());
        commentsHelpDisk.setHelpDisk(helpDisk);
        commentsHelpDisk.setFullNameUser(fullName);
        helpDiskRepository.save(helpDisk);
        commentsHelpDiskRepository.save(commentsHelpDisk);
    }
}
