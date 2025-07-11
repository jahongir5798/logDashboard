package uz.jahonservice.dashboard.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogDto {

    private UUID id;

    //todo: umumiy vaqt va log tavsilotlari
    private LocalDate date;   //log yozilgan kun

    private LocalTime time;   // log yozilgan mahalliy soat

    private long eventTime;   // voqea vaqtim nanosekundlarda

    private String tz;        //UTC farqi masalan "+0500" keladi

    private String logId;     // log identifikatori, ya`ni log turiga mos noyob identifikatr

    private String type;      // trafik berilgan nima ekanligidan xabarim yuq

    private String subType;   // sub turi masalan forwar yoki insight

    private String level;     // notice kelgan nimaligini bilmayman


    //todo:  tarmoq interfeysi manzillari
    private String vd;        //virtual domen

    private Long identifier;

    @Pattern(regexp = "(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}")
    private String srcIP;     // manba IP(sourse IP)

    private String srcName;   // manba kompyuter nomi

    private Long srcPort;   // manbada sessiya uchun ochilgan port

    private String srcIntf;   // manba interfeysi (port1, port2)

    private String srcIntfRole; // manba porti qanaqa (wan, lan..)

    @Pattern(regexp = "(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}")
    private String dstIP;     // maqsad IP(destination IP)

    private Long dstPort;  // maqsad porti

    private String dstIntf;   // maqsad interfeysi, chiqib ketayotgan interfays

    private String dstIntfRole; // maqsad porti qanaqa (wan, lan..)

    private String srcCountry;  // manba mamlakati

    private String dstCountry;  // maqsad mamlakati



    //todo: sessiya va siyosat tafsilotlari
    private Long sessionId;     // sessiya id

    private Long proto;         // protokol

    private String action;      // sessiya holati

    private Long policyId;      // ruxsat berilgan siyosat(policy) identifikatori

    private String policyType;  // siyosat turi

    private String polUUID;     // unikal siyosat identifikatori

    private String policyName;  // qo`llanilgan siyosat nomi

    private String service;     // qo`llanilgan xizmat turi


    //todo: NAT va trafik tafsilotlari
    private String tranDisP;   // translate distination protokol, qanday nat turi qo`llanilgan

    @Pattern(regexp = "(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}")
    private String transIP;    // nat bergan ip manzil

    private Long transport;    // tashqi port, nat began port

    private Long duration;     // sessiya davomiyligi sekundlarda

    private Long sentByte;     // klient serverga yuborgan bayt

    private Long rcvdbyte;     // recieved byte serverdan klient olgan bayt

    private Long sentPkt;      // yuborilgan paketlar soni

    private Long rcvdPkt;      // qabul qilingan paketlar



    //todo: qurilma identifikatsiyasi
    private String appCat;    // ilova kategoriyasi

    private String utmAction;

    private Long countWeb;


    private String srchwVendor; // qurilma ishlab chiqaruvchi

    private String osName;      //qurilma operatsion tizii

    private String srcswVersion;  // qurilma versiyasi

    private String masterSrcMac; // qaysidir max manzil

    @Pattern(regexp = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$|^([0-9A-Fa-f]{4}\\.){2}[0-9A-Fa-f]{4}$")
    private String srcMac;       // qurilma mac manzili

    private Long srcserver;      // serverflag qurilma qanday ravishda ishlayotganini kursatadi 1-server 0-client

    //todo: utmref

}
