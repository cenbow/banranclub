package com.pub.common.tools.opensymphony.web.tag;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pub.common.tools.codestring.SelectCsBean;


/**
 * 下拉选择标签处理类
 * 
 *根据CodeString生成select标签
 *
 * @author liyi.wang
 */
public class SelectBoxTag extends TagSupport implements DynamicAttributes {

    private static final long serialVersionUID = 1L;
    // 日志记录器
    protected static final Log log = LogFactory.getLog(SelectBoxTag.class);

    // 下拉选择项
    private List<SelectCsBean> items;

    // 其它动态属性
    private Map<String, String> attrs = new HashMap<String, String>();

    @Override
    public int doStartTag() throws JspException {

//    	System.out.print("<SelectBoxTag-------");
//    	System.out.print(this.getId());
//    	System.out.print("-------SelectBoxTag>");
    	
        try {
            JspWriter out = pageContext.getOut();
            StringBuilder select = new StringBuilder();
            select.append("<select");
            for (String localName : attrs.keySet()) {
                select.append(" ");
                select.append(localName);
                select.append("=");
                select.append("\"");
                select.append(attrs.get(localName));
                select.append("\"");
            }
            if (StringUtils.isNotBlank(getId())) {
            	//linux + resin 中id不能在动态属性中取到，但是在id属性中可以取得
            	//window + tomcat中正好相反
            	select.append(" ");
                select.append("id");
                select.append("=");
                select.append("\"");
                select.append(getId());
                select.append("\"");
            }
            
            select.append(">");
            //select开始
            out.println(String.valueOf(select));

            //select选项
            if (items != null) {
                for (SelectCsBean csBean : items) {
                    StringBuilder option = new StringBuilder();
                    option.append("<option value='");
                    option.append(csBean.getKey());
                    option.append("' label='");
                    option.append(csBean.getValue());
                    option.append("'");
                    if (csBean.isSelected()) {
                        option.append(" selected='selected'");
                    }
                    option.append(">");
                    option.append(csBean.getValue());
                    option.append("</option>");

                    out.println(String.valueOf(option));
                }
            }

            //select结束
            out.println("</select>");

        } catch (Exception e) {
            throw new JspException(e.getMessage());
        }

        return super.doStartTag();
    }

    public void setDynamicAttribute(String uri, String localName, Object value) throws JspException {
        attrs.put(localName, String.valueOf(value));
    }

    public void setItems(List<SelectCsBean> items) {
        this.items = items;
    }
}
