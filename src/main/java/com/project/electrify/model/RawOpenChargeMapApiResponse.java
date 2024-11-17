package com.project.electrify.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Getter
@Setter
@Document(collection = "rawOpenChargeMapApiResponses")
public class RawOpenChargeMapApiResponse {

    @Id
    private String id;

    @Field("DataProvider")
    private DataProvider dataProvider;

    @Field("OperatorInfo")
    private OperatorInfo operatorInfo;

    @Field("UsageType")
    private UsageType usageType;

    @Field("StatusType")
    private StatusType statusType;

    @Field("SubmissionStatus")
    private SubmissionStatus submissionStatus;

    @Field("UserComments")
    private String userComments;

    @Field("PercentageSimilarity")
    private Double percentageSimilarity;

    @Field("MediaItems")
    private List<String> mediaItems;

    @Field("IsRecentlyVerified")
    private Boolean isRecentlyVerified;

    @Field("DateLastVerified")
    private String dateLastVerified;

    @Field("UUID")
    private String uuid;

    @Field("ParentChargePointID")
    private String parentChargePointID;

    @Field("DataProviderID")
    private Integer dataProviderID;

    @Field("DataProvidersReference")
    private String dataProvidersReference;

    @Field("OperatorID")
    private Integer operatorID;

    @Field("OperatorsReference")
    private String operatorsReference;

    @Field("UsageTypeID")
    private Integer usageTypeID;

    @Field("UsageCost")
    private String usageCost;

    @Field("AddressInfo")
    private AddressInfo addressInfo;

    @Field("Connections")
    private List<Connection> connections;

    @Field("NumberOfPoints")
    private Integer numberOfPoints;

    @Field("GeneralComments")
    private String generalComments;

    @Field("DatePlanned")
    private String datePlanned;

    @Field("DateLastConfirmed")
    private String dateLastConfirmed;

    @Field("StatusTypeID")
    private Integer statusTypeID;

    @Field("DateLastStatusUpdate")
    private String dateLastStatusUpdate;

    @Field("MetadataValues")
    private List<String> metadataValues;

    @Field("DataQualityLevel")
    private Integer dataQualityLevel;

    @Field("DateCreated")
    private String dateCreated;

    @Field("SubmissionStatusTypeID")
    private Integer submissionStatusTypeID;

    @Data
    public static class DataProvider {
        @Field("WebsiteURL")
        private String websiteURL;
        @Field("Comments")
        private String comments;
        @Field("DataProviderStatusType")
        private DataProviderStatusType dataProviderStatusType;
        @Field("IsRestrictedEdit")
        private Boolean isRestrictedEdit;
        @Field("IsOpenDataLicensed")
        private Boolean isOpenDataLicensed;
        @Field("IsApprovedImport")
        private Boolean isApprovedImport;
        @Field("License")
        private String license;
        @Field("DateLastImported")
        private String dateLastImported;
        @Field("ID")
        private Integer id;
        @Field("Title")
        private String title;
    }

    @Data
    public static class OperatorInfo {
        @Field("WebsiteURL")
        private String websiteURL;
        @Field("Comments")
        private String comments;
        @Field("PhonePrimaryContact")
        private String phonePrimaryContact;
        @Field("PhoneSecondaryContact")
        private String phoneSecondaryContact;
        @Field("IsPrivateIndividual")
        private Boolean isPrivateIndividual;
        @Field("AddressInfo")
        private String addressInfo;
        @Field("BookingURL")
        private String bookingURL;
        @Field("ContactEmail")
        private String contactEmail;
        @Field("FaultReportEmail")
        private String faultReportEmail;
        @Field("IsRestrictedEdit")
        private Boolean isRestrictedEdit;
        @Field("ID")
        private Integer id;
        @Field("Title")
        private String title;
    }

    @Data
    public static class UsageType {
        @Field("IsPayAtLocation")
        private Boolean isPayAtLocation;
        @Field("IsMembershipRequired")
        private Boolean isMembershipRequired;
        @Field("IsAccessKeyRequired")
        private Boolean isAccessKeyRequired;
        @Field("ID")
        private Integer id;
        @Field("Title")
        private String title;
    }

    @Data
    public static class StatusType {
        @Field("IsOperational")
        private Boolean isOperational;
        @Field("IsUserSelectable")
        private Boolean isUserSelectable;
        @Field("ID")
        private Integer id;
        @Field("Title")
        private String title;
    }

    @Data
    public static class SubmissionStatus {
        @Field("IsLive")
        private Boolean isLive;
        @Field("ID")
        private Integer id;
        @Field("Title")
        private String title;
    }

    @Data
    public static class AddressInfo {
        @Field("ID")
        private Integer id;
        @Field("Title")
        private String title;
        @Field("AddressLine1")
        private String addressLine1;
        @Field("AddressLine2")
        private String addressLine2;
        @Field("Town")
        private String town;
        @Field("StateOrProvince")
        private String stateOrProvince;
        @Field("Postcode")
        private String postcode;
        @Field("CountryID")
        private Integer countryID;
        @Field("Country")
        private Country country;
        @Field("Latitude")
        private Double latitude;
        @Field("Longitude")
        private Double longitude;
        @Field("ContactTelephone1")
        private String contactTelephone1;
        @Field("ContactTelephone2")
        private String contactTelephone2;
        @Field("ContactEmail")
        private String contactEmail;
        @Field("AccessComments")
        private String accessComments;
        @Field("RelatedURL")
        private String relatedURL;
        @Field("Distance")
        private Double distance;
        @Field("DistanceUnit")
        private Integer distanceUnit;
    }

    @Data
    public static class Country {
        @Field("ISOCode")
        private String isoCode;
        @Field("ContinentCode")
        private String continentCode;
        @Field("ID")
        private Integer id;
        @Field("Title")
        private String title;
    }

    @Data
    public static class Connection {
        @Field("ID")
        private Integer id;
        @Field("ConnectionTypeID")
        private Integer connectionTypeID;
        @Field("ConnectionType")
        private ConnectionType connectionType;
        @Field("Reference")
        private String reference;
        @Field("StatusTypeID")
        private Integer statusTypeID;
        @Field("StatusType")
        private StatusType statusType;
        @Field("LevelID")
        private Integer levelID;
        @Field("Level")
        private Level level;
        @Field("Amps")
        private Integer amps;
        @Field("Voltage")
        private Integer voltage;
        @Field("PowerKW")
        private Double powerKW;
        @Field("CurrentTypeID")
        private Integer currentTypeID;
        @Field("CurrentType")
        private CurrentType currentType;
        @Field("Quantity")
        private Integer quantity;
        @Field("Comments")
        private String comments;
    }

    @Data
    public static class ConnectionType {
        @Field("FormalName")
        private String formalName;
        @Field("IsDiscontinued")
        private Boolean isDiscontinued;
        @Field("IsObsolete")
        private Boolean isObsolete;
        @Field("ID")
        private Integer id;
        @Field("Title")
        private String title;
    }

    @Data
    public static class Level {
        @Field("Comments")
        private String comments;
        @Field("IsFastChargeCapable")
        private Boolean isFastChargeCapable;
        @Field("ID")
        private Integer id;
        @Field("Title")
        private String title;
    }

    @Data
    public static class CurrentType {
        @Field("Description")
        private String description;
        @Field("ID")
        private Integer id;
        @Field("Title")
        private String title;
    }

    @Data
    public static class DataProviderStatusType {
        @Field("IsProviderEnabled")
        private Boolean isProviderEnabled;
        @Field("ID")
        private Integer id;
        @Field("Title")
        private String title;
    }
}
