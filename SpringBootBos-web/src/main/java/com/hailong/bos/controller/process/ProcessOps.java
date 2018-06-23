package com.hailong.bos.controller.process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hailong.bos.domain.process.Deploment;

/**
 * 和流程相关的操作
 * @author Administrator
 *
 */

@RestController	 
@RequestMapping("/process")
public class ProcessOps {
	
	//得到流程引擎对象
	@Autowired
	private RepositoryService repositoryService;
	
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	/**
	 * 和我们的流程相关的操作
	 * @param file
	 * @return
	 */
	@RequestMapping("/processdefinitionDeploy.action")
	public ModelAndView processdefinitionDeploy(MultipartFile deploy){
	    if(deploy!=null){
	    	ZipInputStream in=null;
			try {
				in=new ZipInputStream(deploy.getInputStream());
				
				Deployment deployment=repositoryService
				.createDeployment()
				.addZipInputStream(in)
				.name("财务报销流程")
				.deploy();
	//			System.out.println(deployment.getName()+""+deployment.getId());

			} catch (IOException e) {
				e.printStackTrace();
			}
			return new ModelAndView("redirect:/process/deploymentProcessList.action");
	    }else{
	    	///WEB-INF\pages        /workflow/processdefinitionDeploy.jsp
	    	///WEB-INF/pages/workflow/processdeinitionList.jsp
	    	return new ModelAndView("workflow/processdefinitionDeploy");
	    }
		
	}
	                // processdefinitionList.action
	@RequestMapping("/deploymentProcessList.action")
	public ModelAndView deploymentProcessList(Model model){
		
		List<ProcessDefinition> list=repositoryService.createProcessDefinitionQuery().list();
		Deploment d=null; 
		List<Deploment> dlist=new ArrayList<Deploment>();
		for(ProcessDefinition p:list){
			d=new Deploment();
			d.setId(p.getId());
			d.setKey(p.getKey());
			d.setName(p.getName());
			d.setImageName(p.getDiagramResourceName());//png
			d.setDeploymentId(p.getDeploymentId());
			d.setVersion(""+p.getVersion());
			dlist.add(d);
		}
		model.addAttribute("processDefinitionsList",dlist);
		return new ModelAndView("workflow/processdefinitionList","",model);
	}
	
	/**
	 * 查看流程图的相关操作
	 * /process/processdefinition_viewpng.action?deploymentId=1&imageResourceName=FinancialReimbursement.bpmn
	 */
	@RequestMapping("/processdefinition_viewpng.action")
	public void processdefinitionViewpPng(String deploymentId,String imageResourceName,HttpServletResponse response){
		InputStream in=repositoryService.getResourceAsStream(deploymentId,imageResourceName);
		OutputStream out=null;
		//设置响应的数据的类型,不是下载类型
		response.setContentType("image/png");
		int len=-1;
		//数组当中的数据内容
		byte[] buffer=new byte[4096];
		try {
			//如果是一些字节的数据信息，只能是使用字节流
			out=response.getOutputStream();
			while((len=in.read(buffer))!=-1){
				out.write(buffer,0,len);//写出数据
			}
		} catch (IOException e) {
			e.printStackTrace();
	    }finally{
	    	try {
	    		if(in!=null){
	    			in.close();
	    		}
	    		if(out!=null){
	    			out.close();
	    		}
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}

}
