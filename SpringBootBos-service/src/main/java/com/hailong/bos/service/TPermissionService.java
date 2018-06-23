package com.hailong.bos.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hailong.bos.domain.TPermissions;
import com.hailong.bos.domain.json.JsonUtils;

public interface TPermissionService {

	public List<TPermissions> getAllTPermission();

	public void saveOrUpdate(TPermissions permission);

	public JsonUtils<TPermissions> pageListTPermission(Integer page, Integer rows);

	public List<TPermissions> getMenuPermissionData(HttpServletRequest request);
}
