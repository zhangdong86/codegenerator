package ${controllerPackage};

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import ${domainClass};
import ${servicePackage}.${domainClassName}Service;

@Controller
@Scope("prototype")
@RequestMapping("/${domainClassName?uncap_first}")
public class ${domainClassName}Controller {
	
	@Autowired
	private ${domainClassName}Service ${domainClassName?uncap_first}Service;

	//增、改
	@RequestMapping({"/save","/update"})
	@ResponseBody
	public Object save(${domainClassName} ${domainClassName?uncap_first}) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			${domainClassName?uncap_first}Service.save(${domainClassName?uncap_first});
			result.put("message", "保存成功!");
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", "保存失败!");
			result.put("success", false);
		}
		return result;
	}
	
	//删
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(${domainClassName} ${domainClassName?uncap_first}) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			${domainClassName?uncap_first}Service.delete(${domainClassName?uncap_first});
			result.put("message", "删除成功!");
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", "删除失败!");
			result.put("success", false);
		}
		return result;
	}
	
	//批量删除
	@RequestMapping("/batchDelete")
	@ResponseBody
	public Object batchDelete(String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			${domainClassName?uncap_first}Service.batchDelete(ids.split(","));
			result.put("message", "删除成功!");
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", "删除失败!");
			result.put("success", false);
		}
		return result;
	}
	
	//查
	@RequestMapping("/findOne")
	@ResponseBody
	public Object findOne(${domainClassName} ${domainClassName?uncap_first}) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			${domainClassName} data = ${domainClassName?uncap_first}Service.findOne(${domainClassName?uncap_first});
			result.put("message", "获取成功!");
			result.put("success", true);
			result.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", "获取失败!");
			result.put("success", false);
		}
		return result;
	}
	
	//查全部
	@RequestMapping("/findAll")
	@ResponseBody
	public Object findAll() {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<${domainClassName}> ${domainClassName?uncap_first}s = ${domainClassName?uncap_first}Service.findAll();
			result.put("message", "获取成功!");
			result.put("success", true);
			result.put("data", ${domainClassName?uncap_first}s);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", "获取失败!");
			result.put("success", false);
		}
		return result;
	}
	
	//分页查
	@RequestMapping("/pageQuery")
	@ResponseBody
	public Object pageQuery(${domainClassName} ${domainClassName?uncap_first},int page,int rows,String sort,String order) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Pageable pageable = null;
			if(sort!=null){
				Direction direction = null;
				if("desc".equals(order)){
					direction = Direction.DESC;
				}else{
					direction = Direction.ASC;
				}
				Order order = new Sort.Order(direction,sort);
				Sort sort = new Sort(order);
				pageable = new PageRequest(page - 1, rows,sort);
			}else{
				pageable = new PageRequest(page - 1, rows);
			}
			Page<${domainClassName}> pageData = null;
			if(${domainClassName?uncap_first}==null){
				pageData = ${domainClassName?uncap_first}Service.pageQuery(pageable);
			}else{
				Specification<${domainClassName}> spec = new Specification<${domainClassName}>() {
					@Override
					public Predicate toPredicate(Root<${domainClassName}> root,
							CriteriaQuery<?> query, CriteriaBuilder cb) {
						List<Predicate> list = new ArrayList<Predicate>();
						<#list fieldNameAndFieldTypeNames as field>
							if (StringUtils.isNotBlank(${domainClassName?uncap_first}.get${field.name?cap_first}())) {
								Predicate p${field_index} = cb.equal(
										root.get("${field.name}").as(${field.type}.class),
										${domainClassName?uncap_first}.get${field.name?cap_first}());
								list.add(p${field_index});
							}
						</#list>
						return cb.and(list.toArray(new Predicate[0]));
					}
				};
				pageData = ${domainClassName?uncap_first}Service.pageQuery(spec,pageable);
			}
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("total", pageData.getTotalElements());
			data.put("rows", pageData.getContent());
			result.put("message", "获取成功!");
			result.put("success", true);
			result.put("data", data);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", "获取失败!");
			result.put("success", false);
		}
		return result;
	}
}