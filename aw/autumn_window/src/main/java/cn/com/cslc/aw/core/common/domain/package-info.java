@GenericGenerator(
  name = "ID_GENERATOR",
  strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
		  parameters = {
				     @Parameter(
				        name = "sequence_name",
				        value = "AW_SEQUENCE"
				     ),
				     @Parameter(
				    	        name = "initial_value",
				    	        value = "10000"
				    ),
				    @Parameter(
				    	        name = "increment_size",
				    	        value = "1"
				    ),
				     @Parameter(
				        name = "optimizer",
				        value = "none"
				     )
				}
  )

package cn.com.cslc.aw.core.common.domain;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;