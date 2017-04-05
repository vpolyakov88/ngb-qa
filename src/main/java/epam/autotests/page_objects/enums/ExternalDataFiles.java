package epam.autotests.page_objects.enums;

public enum ExternalDataFiles {

	SMPL1_LUMPY("Sample_1-lumpy.csv"),
	SMPL1_MANTA("Sample_1-manta.csv"),
	SMPL2_LUMPY("Sample_2-lumpy.csv"),
	SMPL2_MANTA("Sample_2-manta.csv");
	
	public final String value;
	
	private ExternalDataFiles(final String value){
		this.value = value;
	}
	
}