package net.risesoft.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import net.risesoft.entity.DocumentCopy;
import net.risesoft.repository.jpa.DocumentCopyRepository;
import net.risesoft.service.DocumentCopyService;
import net.risesoft.service.event.Y9TodoCreatedEvent;
import net.risesoft.service.event.Y9TodoDeletedEvent;
import net.risesoft.y9.Y9Context;

/**
 * @author : qinman
 * @date : 2025-02-10
 * @since 9.6.8
 **/
@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DocumentCopyServiceImpl implements DocumentCopyService {

    private final DocumentCopyRepository documentCopyRepository;

    @Override
    public Page<DocumentCopy> findByUserIdAndStatusLessThan(String assignee, Integer status, int rows, int page,
        Sort sort) {
        PageRequest pageable = PageRequest.of(page > 0 ? page - 1 : 0, rows, sort);
        return documentCopyRepository.findByUserIdAndStatusLessThan(assignee, status, pageable);
    }

    @Override
    public List<DocumentCopy> findByProcessSerialNumberAndSenderId(String processSerialNumber, String senderId) {
        return documentCopyRepository.findByProcessSerialNumberAndSenderIdOrderByCreateTimeAsc(processSerialNumber,
            senderId);
    }

    @Override
    public List<DocumentCopy> findByOpinionCopyId(String opinionCopyId) {
        return documentCopyRepository.findByOpinionCopyIdOrderByCreateTimeAsc(opinionCopyId);
    }

    @Override
    public List<DocumentCopy> findByProcessSerialNumberAndUserIdAndStatus(String processSerialNumber, String userId,
        Integer status) {
        return documentCopyRepository.findByProcessSerialNumberAndUserIdAndStatus(processSerialNumber, userId, status);
    }

    @Override
    public List<DocumentCopy> findByProcessSerialNumberAndUserId(String processSerialNumber, String userId) {
        return documentCopyRepository.findByProcessSerialNumberAndUserId(processSerialNumber, userId);
    }

    @Override
    @Transactional
    public void save(List<DocumentCopy> documentCopyList) {
        documentCopyRepository.saveAll(documentCopyList);
        documentCopyList.forEach(this::publishEvent);
    }

    @Override
    @Transactional
    public void save(DocumentCopy documentCopy) {
        documentCopyRepository.save(documentCopy);
        publishEvent(documentCopy);
    }

    private void publishEvent(DocumentCopy documentCopy) {
        switch (documentCopy.getStatus()) {
            case 1:
                Y9Context.publishEvent(new Y9TodoCreatedEvent<>(documentCopy));
                break;
            case 2:
            case 8:
            case 9:
                Y9Context.publishEvent(new Y9TodoDeletedEvent<>(documentCopy));
                break;
        }
    }

    @Override
    public Optional<DocumentCopy> findById(String id) {
        return documentCopyRepository.findById(id);
    }
}
