package cn.com.cslc.aw.core.common.datatables;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Page;

public class DataTablesResponse<T> {

	private Integer draw;

	private T data;

	private Long recordsTotal;

	private Long recordsFiltered;

	public static <T> DataTablesResponse<T> newDataTablesResponseFor(DataTablesRequest request) {
		DataTablesResponse<T> r = new DataTablesResponse<>();
		r.setDraw(request.getDraw());
		return r;
	}

	public static <T> DataTablesResponse<List<T>> generateDTResponse(DataTablesRequest request, Page<T> page) {
		DataTablesResponse<List<T>> response = newDataTablesResponseFor(request);
		response.setData(page.getContent());
		response.setRecordsTotal(page.getTotalElements());
		response.setRecordsFiltered(page.getTotalElements());
		return response;
	}

	public void setDraw(Integer draw) {
		this.draw = draw;
	}

	public Integer getDraw() {
		return draw;
	}

	public void setData(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setRecordsTotal(Long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public Long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsFiltered(Long recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

	public Long getRecordsFiltered() {
		return recordsFiltered;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof DataTablesResponse) {
			@SuppressWarnings("unchecked")
			DataTablesResponse<T> that = (DataTablesResponse<T>) obj;
			return Objects.equals(this.draw, that.draw) && Objects.equals(this.data, that.data)
					&& Objects.equals(this.recordsTotal, that.recordsTotal)
					&& Objects.equals(this.recordsFiltered, that.recordsFiltered);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.draw, this.data, this.recordsTotal, this.recordsFiltered);
	}

}
