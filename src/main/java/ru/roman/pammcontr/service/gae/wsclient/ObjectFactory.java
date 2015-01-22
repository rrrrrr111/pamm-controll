
package ru.roman.pammcontr.service.gae.wsclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.roman.pammcontr.service.gae.wsclient package.
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _StoreSettings_QNAME = new QName("http://dataws.service.server.bim.roman.ru/", "storeSettings");
    private final static QName _GetList_QNAME = new QName("http://dataws.service.server.bim.roman.ru/", "getList");
    private final static QName _GetListResponse_QNAME = new QName("http://dataws.service.server.bim.roman.ru/", "getListResponse");
    private final static QName _SystemTaskResponse_QNAME = new QName("http://dataws.service.server.bim.roman.ru/", "systemTaskResponse");
    private final static QName _RenewRatingResponse_QNAME = new QName("http://dataws.service.server.bim.roman.ru/", "renewRatingResponse");
    private final static QName _RegisterNewAndLoadSettings_QNAME = new QName("http://dataws.service.server.bim.roman.ru/", "registerNewAndLoadSettings");
    private final static QName _SystemTask_QNAME = new QName("http://dataws.service.server.bim.roman.ru/", "systemTask");
    private final static QName _RegisterNewAndLoadSettingsResponse_QNAME = new QName("http://dataws.service.server.bim.roman.ru/", "registerNewAndLoadSettingsResponse");
    private final static QName _SaveResponse_QNAME = new QName("http://dataws.service.server.bim.roman.ru/", "saveResponse");
    private final static QName _RenewRating_QNAME = new QName("http://dataws.service.server.bim.roman.ru/", "renewRating");
    private final static QName _Save_QNAME = new QName("http://dataws.service.server.bim.roman.ru/", "save");
    private final static QName _StoreSettingsResponse_QNAME = new QName("http://dataws.service.server.bim.roman.ru/", "storeSettingsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.roman.pammcontr.service.gae.wsclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SystemTask }
     * 
     */
    public SystemTask createSystemTask() {
        return new SystemTask();
    }

    /**
     * Create an instance of {@link Save }
     * 
     */
    public Save createSave() {
        return new Save();
    }

    /**
     * Create an instance of {@link RenewRating }
     * 
     */
    public RenewRating createRenewRating() {
        return new RenewRating();
    }

    /**
     * Create an instance of {@link SaveResponse }
     * 
     */
    public SaveResponse createSaveResponse() {
        return new SaveResponse();
    }

    /**
     * Create an instance of {@link RegisterNewAndLoadSettingsResponse }
     * 
     */
    public RegisterNewAndLoadSettingsResponse createRegisterNewAndLoadSettingsResponse() {
        return new RegisterNewAndLoadSettingsResponse();
    }

    /**
     * Create an instance of {@link StoreSettingsResponse }
     * 
     */
    public StoreSettingsResponse createStoreSettingsResponse() {
        return new StoreSettingsResponse();
    }

    /**
     * Create an instance of {@link GetListResponse }
     * 
     */
    public GetListResponse createGetListResponse() {
        return new GetListResponse();
    }

    /**
     * Create an instance of {@link GetList }
     * 
     */
    public GetList createGetList() {
        return new GetList();
    }

    /**
     * Create an instance of {@link StoreSettings }
     * 
     */
    public StoreSettings createStoreSettings() {
        return new StoreSettings();
    }

    /**
     * Create an instance of {@link SystemTaskResponse }
     * 
     */
    public SystemTaskResponse createSystemTaskResponse() {
        return new SystemTaskResponse();
    }

    /**
     * Create an instance of {@link RenewRatingResponse }
     * 
     */
    public RenewRatingResponse createRenewRatingResponse() {
        return new RenewRatingResponse();
    }

    /**
     * Create an instance of {@link RegisterNewAndLoadSettings }
     * 
     */
    public RegisterNewAndLoadSettings createRegisterNewAndLoadSettings() {
        return new RegisterNewAndLoadSettings();
    }

    /**
     * Create an instance of {@link RegisterNewAndLoadSettingsResp }
     * 
     */
    public RegisterNewAndLoadSettingsResp createRegisterNewAndLoadSettingsResp() {
        return new RegisterNewAndLoadSettingsResp();
    }

    /**
     * Create an instance of {@link GetListRequest }
     * 
     */
    public GetListRequest createGetListRequest() {
        return new GetListRequest();
    }

    /**
     * Create an instance of {@link RequestInfo }
     * 
     */
    public RequestInfo createRequestInfo() {
        return new RequestInfo();
    }

    /**
     * Create an instance of {@link AbstractResponse }
     * 
     */
    public AbstractResponse createAbstractResponse() {
        return new AbstractResponse();
    }

    /**
     * Create an instance of {@link AbstractRequest }
     * 
     */
    public AbstractRequest createAbstractRequest() {
        return new AbstractRequest();
    }

    /**
     * Create an instance of {@link SystemTaskResp }
     * 
     */
    public SystemTaskResp createSystemTaskResp() {
        return new SystemTaskResp();
    }

    /**
     * Create an instance of {@link SaveResp }
     * 
     */
    public SaveResp createSaveResp() {
        return new SaveResp();
    }

    /**
     * Create an instance of {@link RenewRatingRequest }
     * 
     */
    public RenewRatingRequest createRenewRatingRequest() {
        return new RenewRatingRequest();
    }

    /**
     * Create an instance of {@link StoreSettingsResp }
     * 
     */
    public StoreSettingsResp createStoreSettingsResp() {
        return new StoreSettingsResp();
    }

    /**
     * Create an instance of {@link GetListResp }
     * 
     */
    public GetListResp createGetListResp() {
        return new GetListResp();
    }

    /**
     * Create an instance of {@link UserSettingsModel }
     * 
     */
    public UserSettingsModel createUserSettingsModel() {
        return new UserSettingsModel();
    }

    /**
     * Create an instance of {@link StoreSettingsRequest }
     * 
     */
    public StoreSettingsRequest createStoreSettingsRequest() {
        return new StoreSettingsRequest();
    }

    /**
     * Create an instance of {@link SystemTaskRequest }
     * 
     */
    public SystemTaskRequest createSystemTaskRequest() {
        return new SystemTaskRequest();
    }

    /**
     * Create an instance of {@link SaveRequest }
     * 
     */
    public SaveRequest createSaveRequest() {
        return new SaveRequest();
    }

    /**
     * Create an instance of {@link BimItemModel }
     * 
     */
    public BimItemModel createBimItemModel() {
        return new BimItemModel();
    }

    /**
     * Create an instance of {@link RegisterNewAndLoadSettingsRequest }
     * 
     */
    public RegisterNewAndLoadSettingsRequest createRegisterNewAndLoadSettingsRequest() {
        return new RegisterNewAndLoadSettingsRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StoreSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dataws.service.server.bim.roman.ru/", name = "storeSettings")
    public JAXBElement<StoreSettings> createStoreSettings(StoreSettings value) {
        return new JAXBElement<StoreSettings>(_StoreSettings_QNAME, StoreSettings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dataws.service.server.bim.roman.ru/", name = "getList")
    public JAXBElement<GetList> createGetList(GetList value) {
        return new JAXBElement<GetList>(_GetList_QNAME, GetList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dataws.service.server.bim.roman.ru/", name = "getListResponse")
    public JAXBElement<GetListResponse> createGetListResponse(GetListResponse value) {
        return new JAXBElement<GetListResponse>(_GetListResponse_QNAME, GetListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SystemTaskResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dataws.service.server.bim.roman.ru/", name = "systemTaskResponse")
    public JAXBElement<SystemTaskResponse> createSystemTaskResponse(SystemTaskResponse value) {
        return new JAXBElement<SystemTaskResponse>(_SystemTaskResponse_QNAME, SystemTaskResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RenewRatingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dataws.service.server.bim.roman.ru/", name = "renewRatingResponse")
    public JAXBElement<RenewRatingResponse> createRenewRatingResponse(RenewRatingResponse value) {
        return new JAXBElement<RenewRatingResponse>(_RenewRatingResponse_QNAME, RenewRatingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterNewAndLoadSettings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dataws.service.server.bim.roman.ru/", name = "registerNewAndLoadSettings")
    public JAXBElement<RegisterNewAndLoadSettings> createRegisterNewAndLoadSettings(RegisterNewAndLoadSettings value) {
        return new JAXBElement<RegisterNewAndLoadSettings>(_RegisterNewAndLoadSettings_QNAME, RegisterNewAndLoadSettings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SystemTask }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dataws.service.server.bim.roman.ru/", name = "systemTask")
    public JAXBElement<SystemTask> createSystemTask(SystemTask value) {
        return new JAXBElement<SystemTask>(_SystemTask_QNAME, SystemTask.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegisterNewAndLoadSettingsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dataws.service.server.bim.roman.ru/", name = "registerNewAndLoadSettingsResponse")
    public JAXBElement<RegisterNewAndLoadSettingsResponse> createRegisterNewAndLoadSettingsResponse(RegisterNewAndLoadSettingsResponse value) {
        return new JAXBElement<RegisterNewAndLoadSettingsResponse>(_RegisterNewAndLoadSettingsResponse_QNAME, RegisterNewAndLoadSettingsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dataws.service.server.bim.roman.ru/", name = "saveResponse")
    public JAXBElement<SaveResponse> createSaveResponse(SaveResponse value) {
        return new JAXBElement<SaveResponse>(_SaveResponse_QNAME, SaveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RenewRating }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dataws.service.server.bim.roman.ru/", name = "renewRating")
    public JAXBElement<RenewRating> createRenewRating(RenewRating value) {
        return new JAXBElement<RenewRating>(_RenewRating_QNAME, RenewRating.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Save }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dataws.service.server.bim.roman.ru/", name = "save")
    public JAXBElement<Save> createSave(Save value) {
        return new JAXBElement<Save>(_Save_QNAME, Save.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StoreSettingsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://dataws.service.server.bim.roman.ru/", name = "storeSettingsResponse")
    public JAXBElement<StoreSettingsResponse> createStoreSettingsResponse(StoreSettingsResponse value) {
        return new JAXBElement<StoreSettingsResponse>(_StoreSettingsResponse_QNAME, StoreSettingsResponse.class, null, value);
    }

}
