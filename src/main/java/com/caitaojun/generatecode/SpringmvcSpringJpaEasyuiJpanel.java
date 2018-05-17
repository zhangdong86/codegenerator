package com.caitaojun.generatecode;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import com.caitaojun.utils.StringUtil;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class SpringmvcSpringJpaEasyuiJpanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField txtComnetdomain;
	private JTextField txtComnetwebcontroller;
	private JTextField txtComnetservice;
	private JTextField txtWebapppages;
	private JTextField txtComnetdao;
	private boolean pathCfg = false;
	private List<Class> classes = new ArrayList<>();
	private String domainPackageStr = null;
	private String controllerPackageStr = null;
	private String servicePackageStr = null;
	private String daoPackageStr = null;
	private String htmlPathStr = null;
	
	public SpringmvcSpringJpaEasyuiJpanel() {
		init();
	}
	
	private void init(){
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_strutsSpringJpaEasyuiJpnel = new GroupLayout(this);
		gl_strutsSpringJpaEasyuiJpnel.setHorizontalGroup(
			gl_strutsSpringJpaEasyuiJpnel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
				.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
				.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 979, Short.MAX_VALUE)
		);
		gl_strutsSpringJpaEasyuiJpnel.setVerticalGroup(
			gl_strutsSpringJpaEasyuiJpnel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_strutsSpringJpaEasyuiJpnel.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE))
		);
		
		final JCheckBox chckbxcover = new JCheckBox("覆盖");
		
		final JCheckBox chckbxcontroller = new JCheckBox("生成controller");
		
		final JCheckBox chckbxservice = new JCheckBox("生成service");
		
		final JCheckBox chckbxhtml = new JCheckBox("生成html");
		
		JButton generateCodeBtn = new JButton("生成");
		
		final JCheckBox chckbxdao = new JCheckBox("生成dao");
		
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addGap(25)
					.addComponent(chckbxcover)
					.addGap(18)
					.addComponent(chckbxcontroller)
					.addGap(18)
					.addComponent(chckbxservice)
					.addGap(18)
					.addComponent(chckbxdao)
					.addGap(18)
					.addComponent(chckbxhtml)
					.addContainerGap(515, Short.MAX_VALUE))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(910, Short.MAX_VALUE)
					.addComponent(generateCodeBtn)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(15, Short.MAX_VALUE)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxcover)
						.addComponent(chckbxcontroller)
						.addComponent(chckbxservice)
						.addComponent(chckbxdao)
						.addComponent(chckbxhtml))
					.addGap(8)
					.addComponent(generateCodeBtn)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		
		JLabel label_1 = new JLabel("实体类选择");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap(461, Short.MAX_VALUE)
					.addComponent(label_1)
					.addGap(456))
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addComponent(label_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
		);
		
		final JPanel domainShowPanel = new JPanel();
		scrollPane.setViewportView(domainShowPanel);
		domainShowPanel.setLayout(new GridLayout(0, 10, 0, 0));
		
		generateCodeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!pathCfg){
					JOptionPane.showMessageDialog(null, "先配置路径,然后点击确定!");
					return;
				}
				ArrayList<String> domains = new ArrayList<String>();
				boolean cover = false;
				boolean generateAction = false;
				boolean generateService = false;
				boolean generateDao = false;
				boolean generateHtml = false;
				for (int i = 0; i < domainShowPanel.getComponentCount(); i++) {
					if (domainShowPanel.getComponent(i) instanceof JCheckBox) {
						if (((JCheckBox) domainShowPanel.getComponent(i)).isSelected()) {
							domains.add(((JCheckBox) domainShowPanel.getComponent(i)).getText());
						}
					}
				}
				if(domains.isEmpty()){
					JOptionPane.showMessageDialog(null, "请选择实体类!");
					return;
				}
				cover = chckbxcover.isSelected();
				generateAction = chckbxcontroller.isSelected();
				generateService = chckbxservice.isSelected();
				generateDao = chckbxdao.isSelected();
				generateHtml = chckbxhtml.isSelected();
				//获取选中的doamin对应的class集合
				List<Class> selectedDomainClass = new ArrayList<>();
				for (Class clazz : classes) {
					for (String doamin : domains) {
						if(clazz.getSimpleName().equals(doamin)){
							selectedDomainClass.add(clazz);
						}
					}
				}
				
				//获取主键  名称  类型
				
				//生成代码
				try {
					if(generateAction){
						generateAction(cover,selectedDomainClass);
					}
					if(generateService){
						generateService(cover,selectedDomainClass);
					}
					if(generateDao){
						generateDao(cover,selectedDomainClass);
					}
					if(generateHtml){
						generateHtml(cover,selectedDomainClass);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}finally{
					JOptionPane.showMessageDialog(null, "生成完毕,请刷新项目!");
				}
			}

			private void generateHtml(boolean cover, List<Class> selectedDomainClass) throws IOException, TemplateException {
				//生成html代码
				Configuration config = new Configuration(Configuration.VERSION_2_3_23);
//				String path = Thread.currentThread().getContextClassLoader().getResource("com/net023/template/no2").getPath();
//				File dir = new File(path);
//				config.setDirectoryForTemplateLoading(dir);
				config.setClassForTemplateLoading(SpringmvcSpringMybatisEasyuiJpanel.class, "/com/caitaojun/template/no2");
				Template template = config.getTemplate("html.ftl");
				Map<String, Object> dataModel = new HashMap<>();
				//controllerPackage  servicePackage htmlPath domainName
				dataModel.put("controllerPackage", controllerPackageStr);
				dataModel.put("doaminPackage", domainPackageStr);
				dataModel.put("servicePackage", servicePackageStr);
				dataModel.put("daoPackage", daoPackageStr);
				for (Class clazz : selectedDomainClass) {
					dataModel.put("doaminClassName", clazz.getSimpleName());
					//获取所有的字段
					List<String>  allFieldNames = getClassAllFieldNames(clazz);
					dataModel.put("fieldNames", allFieldNames);
					//D:\ProgramFiles\workspace\czbk\generatecode\target\classes
//					String canonicalPath = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()).getCanonicalPath();
//					System.out.println(canonicalPath);
					//D:\ProgramFiles\workspace\czbk\generatecode
					String projectPath = System.getProperty("user.dir");
					String htmlPath = htmlPathStr.replace("/", "\\");
					String htmlFilePath = projectPath+"\\src\\main"+"\\"+htmlPath;
					File file = new File(htmlFilePath);
					if(!file.exists()){
						file.mkdirs();
					}
					File htmlFile = new File(file, clazz.getSimpleName()+".html");
					if(cover){//如果存在和要覆盖
						Writer out = new FileWriter(htmlFile);
						template.process(dataModel, out);
						out.close();
					}else{
						if(!htmlFile.exists()){
							//如果不存在就创建 ，存在就跳过
							Writer out = new FileWriter(htmlFile);
							template.process(dataModel, out);
							out.close();
						}
					}
				}
			}
			
			private List<String> getClassAllFieldNames(Class clazz) {
				Field[] declaredFields = clazz.getDeclaredFields();
				List<String> fieldNames = new ArrayList<>();
				for (Field field : declaredFields) {
					fieldNames.add(field.getName());
				}
				return fieldNames;
			}

			private void generateDao(boolean cover, List<Class> selectedDomainClass) throws IOException, TemplateException {
				//生成dao代码
				Configuration config = new Configuration(Configuration.VERSION_2_3_23);
//				String path = Thread.currentThread().getContextClassLoader().getResource("com/net023/template/no2").getPath();
//				File dir = new File(path);
//				config.setDirectoryForTemplateLoading(dir);
				config.setClassForTemplateLoading(SpringmvcSpringMybatisEasyuiJpanel.class, "/com/caitaojun/template/no2");
				Template template = config.getTemplate("dao.ftl");
				Map<String, Object> dataModel = new HashMap<>();
				//controllerPackage  servicePackage htmlPath domainName
				dataModel.put("controllerPackage", controllerPackageStr);
				dataModel.put("doaminPackage", domainPackageStr);
				dataModel.put("servicePackage", servicePackageStr);
				dataModel.put("daoPackage", daoPackageStr);
				for (Class clazz : selectedDomainClass) {
					dataModel.put("doaminClassName", clazz.getSimpleName());
					dataModel.put("primaryKeyJavaType", getClassPrimaryKeyTypeName(clazz).get("type"));
					//D:\ProgramFiles\workspace\czbk\generatecode\target\classes
//					String canonicalPath = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()).getCanonicalPath();
//					System.out.println(canonicalPath);
					//D:\ProgramFiles\workspace\czbk\generatecode
					String projectPath = System.getProperty("user.dir");
					String daoPath = daoPackageStr.replace(".", "\\");
					String daoFilePath = projectPath+"\\src\\main\\java"+"\\"+daoPath;
					File file = new File(daoFilePath);
					if(!file.exists()){
						file.mkdirs();
					}
					File daoFile = new File(file, clazz.getSimpleName()+"Repository.java");
					if(cover){//如果存在和要覆盖
						Writer out = new FileWriter(daoFile);
						template.process(dataModel, out);
						out.close();
					}else{
						if(!daoFile.exists()){
							//如果不存在就创建 ，存在就跳过
							Writer out = new FileWriter(daoFile);
							template.process(dataModel, out);
							out.close();
						}
					}
				}
			}

			private Map<String, String> getClassPrimaryKeyTypeName(Class clazz) {
				Field[] declaredFields = clazz.getDeclaredFields();
				//javax.persistence.Id
				Map<String, String> primaryKeyData = new HashMap<>();
				tag:
				for (Field property : declaredFields) {
					property.setAccessible(true);
					Annotation[] declaredAnnotations = property.getDeclaredAnnotations();
					for (Annotation annotation : declaredAnnotations) {
						if(annotation.annotationType().getName().equals("javax.persistence.Id")){
							primaryKeyData.put("name", property.getName());
							primaryKeyData.put("type", property.getType().getSimpleName());
							break tag;
						}
					}
				}
				if(primaryKeyData.get("name")==null){
					JOptionPane.showMessageDialog(null, "jpa要求PoJo实体类设置id");
					throw new RuntimeException("jpa要求PoJo实体类设置id");
				}
				return primaryKeyData;
			}

			private void generateService(boolean cover, List<Class> selectedDomainClass) throws IOException, TemplateException {
				//生成service代码
				Configuration config = new Configuration(Configuration.VERSION_2_3_23);
//				String path = Thread.currentThread().getContextClassLoader().getResource("com/net023/template/no2").getPath();
//				File dir = new File(path);
//				config.setDirectoryForTemplateLoading(dir);
				config.setClassForTemplateLoading(SpringmvcSpringMybatisEasyuiJpanel.class, "/com/caitaojun/template/no2");
				Template template = config.getTemplate("service.ftl");
				Template template2 = config.getTemplate("serviceimpl.ftl");
				Map<String, Object> dataModel = new HashMap<>();
				//controllerPackage  servicePackage htmlPath domainName
				dataModel.put("controllerPackage", controllerPackageStr);
				dataModel.put("doaminPackage", domainPackageStr);
				dataModel.put("servicePackage", servicePackageStr);
				dataModel.put("daoPackage", daoPackageStr);
				for (Class clazz : selectedDomainClass) {
					dataModel.put("doaminClassName", clazz.getSimpleName());
					dataModel.put("primaryKeyJavaName", StringUtil.changeFirstCharToUpper(getClassPrimaryKeyTypeName(clazz).get("name")));
					//D:\ProgramFiles\workspace\czbk\generatecode\target\classes
//					String canonicalPath = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()).getCanonicalPath();
//					System.out.println(canonicalPath);
					//D:\ProgramFiles\workspace\czbk\generatecode
					String projectPath = System.getProperty("user.dir");
					String servicePath = servicePackageStr.replace(".", "\\");
					String serviceFilePath = projectPath+"\\src\\main\\java"+"\\"+servicePath;
					File file = new File(serviceFilePath);
					if(!file.exists()){
						file.mkdirs();
					}
					//生成接口
					File serviceFile = new File(file, clazz.getSimpleName()+"Service.java");
					if(cover){//如果存在和要覆盖
						Writer out = new FileWriter(serviceFile);
						template.process(dataModel, out);
						out.close();
					}else{
						if(!serviceFile.exists()){
							//如果不存在就创建 ，存在就跳过
							Writer out = new FileWriter(serviceFile);
							template.process(dataModel, out);
							out.close();
						}
					}
					
					//生成实现类
					String serviceImplFilePath = projectPath+"\\src\\main\\java"+"\\"+servicePath+"\\impl";
					File serviceImplPathFile = new File(serviceImplFilePath);
					if(!serviceImplPathFile.exists()){
						serviceImplPathFile.mkdirs();
					}
					File serviceImplFile = new File(serviceImplPathFile, clazz.getSimpleName()+"ServiceImpl.java");
					if(cover){//如果存在和要覆盖
						Writer out = new FileWriter(serviceImplFile);
						template2.process(dataModel, out);
						out.close();
					}else{
						if(!serviceImplFile.exists()){
							//如果不存在就创建 ，存在就跳过
							Writer out = new FileWriter(serviceImplFile);
							template2.process(dataModel, out);
							out.close();
						}
					}
				}
			}

			private void generateAction(boolean cover, List<Class> selectedDomainClass) throws IOException, TemplateException {
				//生成controller代码
				Configuration config = new Configuration(Configuration.VERSION_2_3_23);
//				String path = Thread.currentThread().getContextClassLoader().getResource("com/net023/template/no2").getPath();
//				File dir = new File(path);
//				config.setDirectoryForTemplateLoading(dir);
				config.setClassForTemplateLoading(SpringmvcSpringMybatisEasyuiJpanel.class, "/com/caitaojun/template/no2");
				Template template = config.getTemplate("controller.ftl");
				Map<String, Object> dataModel = new HashMap<>();
				//controllerPackage  servicePackage htmlPath domainName
				dataModel.put("controllerPackage", controllerPackageStr);
				dataModel.put("doaminPackage", domainPackageStr);
				dataModel.put("servicePackage", servicePackageStr);
				dataModel.put("daoPackage", daoPackageStr);
				for (Class clazz : selectedDomainClass) {
					dataModel.put("doaminClassName", clazz.getSimpleName());
//					dataModel.put("primaryKeyJavaTypeName", getClassPrimaryKeyTypeName(clazz).get("type"));
					//D:\ProgramFiles\workspace\czbk\generatecode\target\classes
//					String canonicalPath = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()).getCanonicalPath();
//					System.out.println(canonicalPath);
					//D:\ProgramFiles\workspace\czbk\generatecode
					String projectPath = System.getProperty("user.dir");
					String actionPath = controllerPackageStr.replace(".", "\\");
					String actionFilePath = projectPath+"\\src\\main\\java"+"\\"+actionPath;
					File file = new File(actionFilePath);
					if(!file.exists()){
						file.mkdirs();
					}
					File actionFile = new File(file, clazz.getSimpleName()+"Controller.java");
					if(cover){//如果存在和要覆盖
						Writer out = new FileWriter(actionFile);
						template.process(dataModel, out);
						out.close();
					}else{
						if(!actionFile.exists()){
							//如果不存在就创建 ，存在就跳过
							Writer out = new FileWriter(actionFile);
							template.process(dataModel, out);
							out.close();
						}
					}
				}
			}
		});
		
		panel_1.setLayout(gl_panel_1);
		
		JLabel label = new JLabel("路径设置");
		
		JLabel lblDomain = new JLabel("domain：");
		
		txtComnetdomain = new JTextField();
		txtComnetdomain.setText("com.net023.domain");
		txtComnetdomain.setColumns(10);
		
		JLabel lblController = new JLabel("controller：");
		
		txtComnetwebcontroller = new JTextField();
		txtComnetwebcontroller.setText("com.net023.web.controller");
		txtComnetwebcontroller.setColumns(10);
		
		JLabel lblService = new JLabel("service：");
		
		txtComnetservice = new JTextField();
		txtComnetservice.setText("com.net023.service");
		txtComnetservice.setColumns(10);
		
		JLabel lblHtml = new JLabel("html：");
		
		txtWebapppages = new JTextField();
		txtWebapppages.setText("webapp/pages");
		txtWebapppages.setColumns(10);
		
		
		JButton pathCfgConfirmBtn = new JButton("确定");
		pathCfgConfirmBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				domainPackageStr = txtComnetdomain.getText().trim();
				controllerPackageStr = txtComnetwebcontroller.getText().trim();
				servicePackageStr = txtComnetservice.getText().trim();
				daoPackageStr = txtComnetdao.getText().trim();
				htmlPathStr = txtWebapppages.getText().trim();
				if("".equals(domainPackageStr)){
					JOptionPane.showMessageDialog(null, "doamin包路径不能为空!");
					return;
				}
				if("".equals(controllerPackageStr)){
					JOptionPane.showMessageDialog(null, "controller包路径不能为空!");
					return;
				}
				if("".equals(servicePackageStr)){
					JOptionPane.showMessageDialog(null, "service包路径不能为空!");
					return;
				}
				if("".equals(daoPackageStr)){
					JOptionPane.showMessageDialog(null, "dao包路径不能为空!");
					return;
				}
				if("".equals(htmlPathStr)){
					JOptionPane.showMessageDialog(null, "html路径不能为空!");
					return;
				}
				pathCfg = true;
				//显示实体类的名称
				try {
					//先清空
					domainShowPanel.removeAll();
					showDomainClassName(domainPackageStr);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			private void showDomainClassName(String domainPackageStr) throws IOException, ClassNotFoundException {
				//根据路径去找domain实体类
				domainPackageStr = domainPackageStr.replace(".", "/");
//				System.out.println(domainPackageStr);
//				System.out.println(System.getProperty("user.dir"));
				URL resource = Thread.currentThread().getContextClassLoader().getResource(domainPackageStr);
				File fileDir = new File(resource.getPath());
				classes = new ArrayList<>();
				findClass(fileDir, classes);
				for (Class clazz: classes) {
//					domainShowPanel.add(clazz.getSimpleName(),new JCheckBox(clazz.getSimpleName()));
					domainShowPanel.add(new JCheckBox(clazz.getSimpleName()));
				}
				domainShowPanel.updateUI();//更新界面
			}
		});
		
		JLabel lblDao = new JLabel("dao：");
		
		txtComnetdao = new JTextField();
		txtComnetdao.setText("com.net023.dao");
		txtComnetdao.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(464)
					.addComponent(label))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(84)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDomain)
						.addComponent(lblService)
						.addComponent(lblDao))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(txtComnetdao)
						.addComponent(txtComnetservice)
						.addComponent(txtComnetdomain, GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
					.addGap(219)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblController)
							.addGap(4)
							.addComponent(txtComnetwebcontroller, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblHtml)
							.addGap(18)
							.addComponent(txtWebapppages)))
					.addPreferredGap(ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
					.addComponent(pathCfgConfirmBtn)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDomain)
						.addComponent(lblController)
						.addComponent(txtComnetwebcontroller, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtComnetdomain, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblService)
						.addComponent(txtComnetservice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHtml)
						.addComponent(txtWebapppages, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDao)
						.addComponent(txtComnetdao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(90, Short.MAX_VALUE)
					.addComponent(pathCfgConfirmBtn)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		setLayout(gl_strutsSpringJpaEasyuiJpnel);
		
	}
	
	private void findClass(File fileDir,List<Class> classList) throws IOException, ClassNotFoundException{
		if(fileDir.isDirectory()){
			File[] listFiles = fileDir.listFiles();
			for (File file : listFiles) {
				findClass(file, classList);
			}
		}else{
			if(fileDir.getName().endsWith(".class")){
//				String classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").getPath()).getCanonicalPath();
				String canonicalPath = fileDir.getCanonicalPath();
//				String classFullName = canonicalPath.substring(classPath.length()+1, canonicalPath.length()-6);
				String[] split = canonicalPath.split("classes");
				String classFullName = split[1].substring(1,split[1].length()-6);
				classFullName = classFullName.replace("\\", ".");
				classList.add(Class.forName(classFullName, false, Thread.currentThread().getContextClassLoader()));
			}
		}
	}
}	