package cn.com.cslc.aw.core.ws.repository;

import java.util.List;

import cn.com.cslc.aw.core.common.domain.SysWsMessage;
import cn.com.cslc.aw.core.common.repository.BaseRepository;

public interface WsMessageRepository extends BaseRepository<SysWsMessage>{
	SysWsMessage findBySerialNumberAndSource(String serialNumber,String source);
}
