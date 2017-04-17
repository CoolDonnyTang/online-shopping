package com.cduestc.tyr.online_shopping.service;

import java.util.List;
import java.util.Map;

public interface ICommEntityService {
	public List<Map<String, String>> findCommEntitiesById(Integer[] ids);
}
