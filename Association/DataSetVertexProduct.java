package Association;

import Association.Main.DataSetVertex;
import Association.Main.ItemVertex;
import Association.Main.LinkItem;

public class DataSetVertexProduct {
	private ItemVertex headVtx;
	private ItemVertex tailVtx;

	public ItemVertex getHeadVtx() {
		return headVtx;
	}

	public void setHeadVtx(ItemVertex headVtx) {
		this.headVtx = headVtx;
	}

	public ItemVertex getTailVtx() {
		return tailVtx;
	}

	public void setTailVtx(ItemVertex tailVtx) {
		this.tailVtx = tailVtx;
	}

	public void printToConsole() {
		System.out.print("[");
		this.displayByRecur(this.headVtx);
	}

	public boolean appendList(String typeItem, DataSetVertex dataSetVertex) {
		ItemVertex typeItemTo_Obj = new ItemVertex(typeItem);
		if (!LinkItem.ifEmptyList(this.headVtx)) {
			tailVtx.setToNext(typeItemTo_Obj);
			dataSetVertex.setTailVtx(tailVtx.getToNext());
			return true;
		}
		dataSetVertex.setHeadVtx(typeItemTo_Obj);
		dataSetVertex.setTailVtx(typeItemTo_Obj);
		return true;
	}

	public void displayByRecur(ItemVertex base) {
		if (base != null) {
			System.out.print(base + (base.getToNext() != null ? " , " : "] "));
			displayByRecur(base.getToNext());
		}
		return;
	}
}