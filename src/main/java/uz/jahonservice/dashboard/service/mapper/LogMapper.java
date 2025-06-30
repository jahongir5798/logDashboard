package uz.jahonservice.dashboard.service.mapper;

import org.springframework.stereotype.Component;
import uz.jahonservice.dashboard.dto.LogDto;
import uz.jahonservice.dashboard.entity.LogEntity;

@Component
public  class LogMapper {

    public  LogDto toDto(LogEntity logEntity){
        LogDto logDto = new LogDto();
        logDto.setUuid(logEntity.getUuid());
        logDto.setDate(logEntity.getDate());
        logDto.setTime(logEntity.getTime());
        logDto.setEventTime(logEntity.getEventTime());
        logDto.setTz(logEntity.getTz());
        logDto.setLogId(logEntity.getLogId());
        logDto.setType(logEntity.getType());
        logDto.setSubType(logEntity.getSubType());
        logDto.setLevel(logEntity.getLevel());

        logDto.setVd(logEntity.getVd());
        logDto.setSrcIP(logEntity.getSrcIP());
        logDto.setSrcName(logEntity.getSrcName());
        logDto.setSrcPort(logEntity.getSrcPort());
        logDto.setSrcIntf(logEntity.getSrcIntf());
        logDto.setSrcIntfRole(logEntity.getSrcIntfRole());
        logDto.setDstIP(logEntity.getDstIP());
        logDto.setDstPort(logEntity.getDstPort());
        logDto.setDstIntf(logEntity.getDstIntf());
        logDto.setDstIntfRole(logEntity.getDstIntfRole());
        logDto.setSrcCountry(logEntity.getSrcCountry());
        logDto.setDstCountry(logEntity.getDstCountry());

        logDto.setSessionId(logEntity.getSessionId());
        logDto.setProto(logEntity.getProto());
        logDto.setAction(logEntity.getAction());
        logDto.setPolicyId(logEntity.getPolicyId());
        logDto.setPolicyType(logEntity.getPolicyType());
        logDto.setPolUUID(logEntity.getPolUUID());
        logDto.setPolicyName(logEntity.getPolicyName());
        logDto.setService(logEntity.getService());

        logDto.setTranDisP(logEntity.getTranDisP());
        logDto.setTransIP(logEntity.getTransIP());
        logDto.setTransport(logEntity.getTransport());
        logDto.setDuration(logEntity.getDuration());
        logDto.setSentByte(logEntity.getSentByte());
        logDto.setRcvdbyte(logEntity.getRcvdbyte());
        logDto.setSentPkt(logEntity.getSentPkt());
        logDto.setRcvdPkt(logEntity.getRcvdPkt());

        logDto.setAppCat(logEntity.getAppCat());
        logDto.setSrchwVendor(logEntity.getSrchwVendor());
        logDto.setOsName(logEntity.getOsName());
        logDto.setSrcsVersion(logEntity.getSrcsVersion());
        logDto.setMasterSrcMac(logEntity.getMasterSrcMac());
        logDto.setSrcMac(logEntity.getSrcMac());
        logDto.setSrcserver(logEntity.getSrcserver());

        return logDto;
    }

}
