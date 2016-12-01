package com.platform.persistence.util.resource.tree;

import java.util.List;

public class Tree {
	
	public Tree(String id,String pid,String text,String code,boolean checked)
	{
		this.id = id;
		this.text = text;
		this.pid = pid;
		this.code = code;
		this.checked = checked;
	}
	
	private String id;//树id
	private String text;//显示的内容
	private String pid; //父级id
	private String code;//编码
	private boolean isLeaf;//是否是叶子节点
	private boolean checked;//选中状态 
	private List<Tree> children;//子数据集合
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public List<Tree> getChildren() {
		return children;
	}
	public void setChildren(List<Tree> children) {
		this.children = children;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
	
}
