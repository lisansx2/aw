package cn.com.cslc.aw.core.common.dto;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.List;

public class BootstrapTreeView implements Serializable {
	private Long id;

	private String text;
	
	private String code;

	private String href = "#";

	private List<BootstrapTreeView> nodes;

	private BootstrapTreeViewNodeState state;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<BootstrapTreeView> getNodes() {
		/*
		 * if (nodes == null) { nodes = Lists.newArrayList(); }
		 */
		return nodes;
	}

	public void setNodes(List<BootstrapTreeView> nodes) {
		this.nodes = nodes;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public BootstrapTreeViewNodeState getState() {
		return state;
	}

	public void setState(BootstrapTreeViewNodeState state) {
		this.state = state;
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BootstrapTreeView deepClone() throws Exception{
		// 将对象写到流里
		OutputStream bo = new ByteArrayOutputStream();
		// OutputStream op = new ObjectOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(this);

		// 从流里读出来
		InputStream bi = new ByteArrayInputStream(((ByteArrayOutputStream) bo).toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return (BootstrapTreeView) (oi.readObject());
	}
}
