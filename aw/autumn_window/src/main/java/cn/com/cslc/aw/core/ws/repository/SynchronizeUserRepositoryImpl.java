package cn.com.cslc.aw.core.ws.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SynchronizeUserRepositoryImpl implements SynchronizeUserRepository{
	
	@Autowired
	private WsMessageRepository wsMessageRepository;
}
