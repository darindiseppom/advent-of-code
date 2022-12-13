package mdrd.advent.of.code.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Day9 {

	static List<Long> input = Arrays.asList(Long.valueOf("17"), Long.valueOf("14"), Long.valueOf("2"),
			Long.valueOf("35"), Long.valueOf("39"), Long.valueOf("31"), Long.valueOf("5"), Long.valueOf("25"),
			Long.valueOf("1"), Long.valueOf("29"), Long.valueOf("40"), Long.valueOf("48"), Long.valueOf("9"),
			Long.valueOf("37"), Long.valueOf("21"), Long.valueOf("7"), Long.valueOf("41"), Long.valueOf("8"),
			Long.valueOf("15"), Long.valueOf("28"), Long.valueOf("47"), Long.valueOf("13"), Long.valueOf("16"),
			Long.valueOf("50"), Long.valueOf("45"), Long.valueOf("30"), Long.valueOf("11"), Long.valueOf("3"),
			Long.valueOf("33"), Long.valueOf("78"), Long.valueOf("4"), Long.valueOf("69"), Long.valueOf("5"),
			Long.valueOf("58"), Long.valueOf("17"), Long.valueOf("19"), Long.valueOf("10"), Long.valueOf("12"),
			Long.valueOf("9"), Long.valueOf("25"), Long.valueOf("18"), Long.valueOf("7"), Long.valueOf("14"),
			Long.valueOf("8"), Long.valueOf("55"), Long.valueOf("21"), Long.valueOf("15"), Long.valueOf("22"),
			Long.valueOf("13"), Long.valueOf("16"), Long.valueOf("70"), Long.valueOf("20"), Long.valueOf("11"),
			Long.valueOf("23"), Long.valueOf("29"), Long.valueOf("24"), Long.valueOf("26"), Long.valueOf("27"),
			Long.valueOf("69"), Long.valueOf("28"), Long.valueOf("17"), Long.valueOf("19"), Long.valueOf("30"),
			Long.valueOf("43"), Long.valueOf("48"), Long.valueOf("25"), Long.valueOf("33"), Long.valueOf("31"),
			Long.valueOf("32"), Long.valueOf("41"), Long.valueOf("39"), Long.valueOf("35"), Long.valueOf("40"),
			Long.valueOf("34"), Long.valueOf("66"), Long.valueOf("36"), Long.valueOf("37"), Long.valueOf("49"),
			Long.valueOf("52"), Long.valueOf("42"), Long.valueOf("44"), Long.valueOf("45"), Long.valueOf("46"),
			Long.valueOf("47"), Long.valueOf("85"), Long.valueOf("51"), Long.valueOf("56"), Long.valueOf("72"),
			Long.valueOf("57"), Long.valueOf("73"), Long.valueOf("67"), Long.valueOf("63"), Long.valueOf("69"),
			Long.valueOf("104"), Long.valueOf("76"), Long.valueOf("70"), Long.valueOf("84"), Long.valueOf("71"),
			Long.valueOf("107"), Long.valueOf("82"), Long.valueOf("78"), Long.valueOf("79"), Long.valueOf("90"),
			Long.valueOf("86"), Long.valueOf("149"), Long.valueOf("110"), Long.valueOf("200"), Long.valueOf("93"),
			Long.valueOf("98"), Long.valueOf("108"), Long.valueOf("113"), Long.valueOf("119"), Long.valueOf("120"),
			Long.valueOf("124"), Long.valueOf("130"), Long.valueOf("132"), Long.valueOf("161"), Long.valueOf("139"),
			Long.valueOf("210"), Long.valueOf("141"), Long.valueOf("148"), Long.valueOf("170"), Long.valueOf("278"),
			Long.valueOf("186"), Long.valueOf("172"), Long.valueOf("157"), Long.valueOf("165"), Long.valueOf("184"),
			Long.valueOf("179"), Long.valueOf("191"), Long.valueOf("201"), Long.valueOf("206"), Long.valueOf("212"),
			Long.valueOf("340"), Long.valueOf("221"), Long.valueOf("232"), Long.valueOf("267"), Long.valueOf("289"),
			Long.valueOf("254"), Long.valueOf("401"), Long.valueOf("271"), Long.valueOf("280"), Long.valueOf("287"),
			Long.valueOf("298"), Long.valueOf("377"), Long.valueOf("322"), Long.valueOf("327"), Long.valueOf("329"),
			Long.valueOf("363"), Long.valueOf("336"), Long.valueOf("468"), Long.valueOf("344"), Long.valueOf("370"),
			Long.valueOf("380"), Long.valueOf("392"), Long.valueOf("407"), Long.valueOf("556"), Long.valueOf("433"),
			Long.valueOf("453"), Long.valueOf("488"), Long.valueOf("521"), Long.valueOf("534"), Long.valueOf("525"),
			Long.valueOf("541"), Long.valueOf("760"), Long.valueOf("551"), Long.valueOf("567"), Long.valueOf("627"),
			Long.valueOf("958"), Long.valueOf("663"), Long.valueOf("649"), Long.valueOf("838"), Long.valueOf("743"),
			Long.valueOf("904"), Long.valueOf("680"), Long.valueOf("714"), Long.valueOf("1055"), Long.valueOf("984"),
			Long.valueOf("772"), Long.valueOf("1231"), Long.valueOf("1248"), Long.valueOf("1756"), Long.valueOf("886"),
			Long.valueOf("941"), Long.valueOf("1009"), Long.valueOf("1046"), Long.valueOf("1059"), Long.valueOf("1118"),
			Long.valueOf("1294"), Long.valueOf("1178"), Long.valueOf("1265"), Long.valueOf("1194"),
			Long.valueOf("1276"), Long.valueOf("1377"), Long.valueOf("2383"), Long.valueOf("1827"),
			Long.valueOf("1689"), Long.valueOf("1394"), Long.valueOf("1726"), Long.valueOf("1452"),
			Long.valueOf("1486"), Long.valueOf("1658"), Long.valueOf("1925"), Long.valueOf("2162"),
			Long.valueOf("1895"), Long.valueOf("2164"), Long.valueOf("2000"), Long.valueOf("1932"),
			Long.valueOf("2055"), Long.valueOf("2068"), Long.valueOf("2105"), Long.valueOf("2177"),
			Long.valueOf("2296"), Long.valueOf("3194"), Long.valueOf("3993"), Long.valueOf("2459"),
			Long.valueOf("2470"), Long.valueOf("2653"), Long.valueOf("2880"), Long.valueOf("3381"),
			Long.valueOf("3178"), Long.valueOf("3110"), Long.valueOf("2846"), Long.valueOf("4836"),
			Long.valueOf("4105"), Long.valueOf("3144"), Long.valueOf("3553"), Long.valueOf("3925"),
			Long.valueOf("3950"), Long.valueOf("3827"), Long.valueOf("3932"), Long.valueOf("3987"),
			Long.valueOf("4000"), Long.valueOf("4123"), Long.valueOf("4564"), Long.valueOf("4282"),
			Long.valueOf("4473"), Long.valueOf("5176"), Long.valueOf("4929"), Long.valueOf("5112"),
			Long.valueOf("9948"), Long.valueOf("5123"), Long.valueOf("5499"), Long.valueOf("5956"),
			Long.valueOf("5990"), Long.valueOf("7060"), Long.valueOf("6254"), Long.valueOf("6673"),
			Long.valueOf("7426"), Long.valueOf("6697"), Long.valueOf("11430"), Long.valueOf("7777"),
			Long.valueOf("7759"), Long.valueOf("7814"), Long.valueOf("7827"), Long.valueOf("7932"),
			Long.valueOf("7987"), Long.valueOf("8123"), Long.valueOf("8405"), Long.valueOf("8755"),
			Long.valueOf("10536"), Long.valueOf("9402"), Long.valueOf("10041"), Long.valueOf("10919"),
			Long.valueOf("13977"), Long.valueOf("10622"), Long.valueOf("12183"), Long.valueOf("11455"),
			Long.valueOf("17386"), Long.valueOf("29569"), Long.valueOf("12927"), Long.valueOf("12951"),
			Long.valueOf("14450"), Long.valueOf("15900"), Long.valueOf("14456"), Long.valueOf("15573"),
			Long.valueOf("15591"), Long.valueOf("24655"), Long.valueOf("15641"), Long.valueOf("15759"),
			Long.valueOf("15919"), Long.valueOf("16110"), Long.valueOf("24993"), Long.valueOf("17160"),
			Long.valueOf("25078"), Long.valueOf("25134"), Long.valueOf("23858"), Long.valueOf("29618"),
			Long.valueOf("21541"), Long.valueOf("41024"), Long.valueOf("27096"), Long.valueOf("30350"),
			Long.valueOf("24382"), Long.valueOf("25878"), Long.valueOf("27377"), Long.valueOf("31491"),
			Long.valueOf("27401"), Long.valueOf("28906"), Long.valueOf("30029"), Long.valueOf("30047"),
			Long.valueOf("31164"), Long.valueOf("31232"), Long.valueOf("31400"), Long.valueOf("45016"),
			Long.valueOf("46778"), Long.valueOf("41018"), Long.valueOf("46139"), Long.valueOf("56228"),
			Long.valueOf("44256"), Long.valueOf("45399"), Long.valueOf("45923"), Long.valueOf("47419"),
			Long.valueOf("86040"), Long.valueOf("48637"), Long.valueOf("50260"), Long.valueOf("51478"),
			Long.valueOf("51759"), Long.valueOf("53288"), Long.valueOf("54784"), Long.valueOf("57406"),
			Long.valueOf("57448"), Long.valueOf("76180"), Long.valueOf("58935"), Long.valueOf("60076"),
			Long.valueOf("95276"), Long.valueOf("62396"), Long.valueOf("137518"), Long.valueOf("72418"),
			Long.valueOf("85274"), Long.valueOf("94197"), Long.valueOf("86417"), Long.valueOf("104332"),
			Long.valueOf("92818"), Long.valueOf("96056"), Long.valueOf("91322"), Long.valueOf("93342"),
			Long.valueOf("155738"), Long.valueOf("98897"), Long.valueOf("100115"), Long.valueOf("213144"),
			Long.valueOf("103237"), Long.valueOf("105047"), Long.valueOf("164408"), Long.valueOf("112190"),
			Long.valueOf("114854"), Long.valueOf("116383"), Long.valueOf("169522"), Long.valueOf("145350"),
			Long.valueOf("157692"), Long.valueOf("185519"), Long.valueOf("134814"), Long.valueOf("228156"),
			Long.valueOf("158835"), Long.valueOf("187378"), Long.valueOf("206387"), Long.valueOf("214969"),
			Long.valueOf("184140"), Long.valueOf("197865"), Long.valueOf("184664"), Long.valueOf("193457"),
			Long.valueOf("192239"), Long.valueOf("262739"), Long.valueOf("205162"), Long.valueOf("203352"),
			Long.valueOf("281712"), Long.valueOf("296854"), Long.valueOf("217237"), Long.valueOf("227044"),
			Long.valueOf("228573"), Long.valueOf("249668"), Long.valueOf("251197"), Long.valueOf("280164"),
			Long.valueOf("415102"), Long.valueOf("371518"), Long.valueOf("318954"), Long.valueOf("413237"),
			Long.valueOf("342975"), Long.valueOf("343499"), Long.valueOf("390730"), Long.valueOf("376379"),
			Long.valueOf("500206"), Long.valueOf("368804"), Long.valueOf("376903"), Long.valueOf("397401"),
			Long.valueOf("443125"), Long.valueOf("420812"), Long.valueOf("408514"), Long.valueOf("507208"),
			Long.valueOf("753282"), Long.valueOf("695857"), Long.valueOf("570543"), Long.valueOf("444281"),
			Long.valueOf("626571"), Long.valueOf("592643"), Long.valueOf("500865"), Long.valueOf("693401"),
			Long.valueOf("599118"), Long.valueOf("661929"), Long.valueOf("814643"), Long.valueOf("662453"),
			Long.valueOf("712303"), Long.valueOf("852795"), Long.valueOf("788131"), Long.valueOf("797715"),
			Long.valueOf("745183"), Long.valueOf("745707"), Long.valueOf("777318"), Long.valueOf("887406"),
			Long.valueOf("1174719"), Long.valueOf("1308218"), Long.valueOf("909379"), Long.valueOf("1101915"),
			Long.valueOf("1458010"), Long.valueOf("1773837"), Long.valueOf("945146"), Long.valueOf("1407636"),
			Long.valueOf("1036924"), Long.valueOf("1099983"), Long.valueOf("1093508"), Long.valueOf("1162794"),
			Long.valueOf("1782631"), Long.valueOf("1344301"), Long.valueOf("1920426"), Long.valueOf("1374756"),
			Long.valueOf("1630113"), Long.valueOf("1457486"), Long.valueOf("1543422"), Long.valueOf("2345142"),
			Long.valueOf("1522501"), Long.valueOf("1690853"), Long.valueOf("1523025"), Long.valueOf("1987389"),
			Long.valueOf("1796785"), Long.valueOf("2494410"), Long.valueOf("2936631"), Long.valueOf("1854525"),
			Long.valueOf("1982070"), Long.valueOf("2038654"), Long.valueOf("3545378"), Long.valueOf("2199718"),
			Long.valueOf("2130432"), Long.valueOf("2136907"), Long.valueOf("2193491"), Long.valueOf("5236231"),
			Long.valueOf("3144864"), Long.valueOf("2719057"), Long.valueOf("2801787"), Long.valueOf("3213354"),
			Long.valueOf("4016911"), Long.valueOf("2979987"), Long.valueOf("4409910"), Long.valueOf("3045526"),
			Long.valueOf("3213878"), Long.valueOf("3653457"), Long.valueOf("3510414"), Long.valueOf("3319810"),
			Long.valueOf("8889688"), Long.valueOf("4232145"), Long.valueOf("3836595"), Long.valueOf("3893179"),
			Long.valueOf("11089406"), Long.valueOf("4020724"), Long.valueOf("4169086"), Long.valueOf("4267339"),
			Long.valueOf("4323923"), Long.valueOf("4330398"), Long.valueOf("5703905"), Long.valueOf("7551955"),
			Long.valueOf("5520844"), Long.valueOf("6739781"), Long.valueOf("5699044"), Long.valueOf("7230789"),
			Long.valueOf("6259404"), Long.valueOf("12906599"), Long.valueOf("8499484"), Long.valueOf("6365336"),
			Long.valueOf("10576376"), Long.valueOf("9788183"), Long.valueOf("10022967"), Long.valueOf("6830224"),
			Long.valueOf("7340534"), Long.valueOf("8068740"), Long.valueOf("7729774"), Long.valueOf("8062265"),
			Long.valueOf("16097220"), Long.valueOf("8189810"), Long.valueOf("8288063"), Long.valueOf("13970570"),
			Long.valueOf("16282371"), Long.valueOf("9851242"), Long.valueOf("11670932"), Long.valueOf("11219888"),
			Long.valueOf("11780248"), Long.valueOf("16275420"), Long.valueOf("11958448"), Long.valueOf("13089628"),
			Long.valueOf("13195560"), Long.valueOf("12624740"), Long.valueOf("13705870"), Long.valueOf("16689294"),
			Long.valueOf("10884537"), Long.valueOf("23758753"), Long.valueOf("14892489"), Long.valueOf("20148258"),
			Long.valueOf("14170758"), Long.valueOf("22555469"), Long.valueOf("24309516"), Long.valueOf("15792039"),
			Long.valueOf("16252075"), Long.valueOf("16477873"), Long.valueOf("18139305"), Long.valueOf("26330610"),
			Long.valueOf("23046802"), Long.valueOf("20735779"), Long.valueOf("21071130"), Long.valueOf("22104425"),
			Long.valueOf("37217560"), Long.valueOf("23509277"), Long.valueOf("22842985"), Long.valueOf("23974165"),
			Long.valueOf("24080097"), Long.valueOf("38651242"), Long.valueOf("30183743"), Long.valueOf("59387021"),
			Long.valueOf("55356865"), Long.valueOf("35963619"), Long.valueOf("35940297"), Long.valueOf("38144923"),
			Long.valueOf("36863169"), Long.valueOf("33931344"), Long.valueOf("32044114"), Long.valueOf("32269912"),
			Long.valueOf("36987854"), Long.valueOf("39761352"), Long.valueOf("68834985"), Long.valueOf("99148373"),
			Long.valueOf("41806909"), Long.valueOf("44117932"), Long.valueOf("42840204"), Long.valueOf("80458151"),
			Long.valueOf("44947410"), Long.valueOf("46352262"), Long.valueOf("75008092"), Long.valueOf("46817150"),
			Long.valueOf("62119088"), Long.valueOf("78049276"), Long.valueOf("62227857"), Long.valueOf("62453655"),
			Long.valueOf("65975458"), Long.valueOf("78803823"), Long.valueOf("64314026"), Long.valueOf("66201256"),
			Long.valueOf("103189110"), Long.valueOf("108596337"), Long.valueOf("69031968"), Long.valueOf("126433114"),
			Long.valueOf("94497769"), Long.valueOf("76749206"), Long.valueOf("81568261"), Long.valueOf("84647113"),
			Long.valueOf("87787614"), Long.valueOf("115849118"), Long.valueOf("86958136"), Long.valueOf("89192466"),
			Long.valueOf("108471350"), Long.valueOf("91299672"), Long.valueOf("93169412"), Long.valueOf("108936238"),
			Long.valueOf("109045007"), Long.valueOf("128320344"), Long.valueOf("124681512"), Long.valueOf("126541883"),
			Long.valueOf("126767681"), Long.valueOf("163529737"), Long.valueOf("169355875"), Long.valueOf("141063232"),
			Long.valueOf("135233224"), Long.valueOf("257143489"), Long.valueOf("211080227"), Long.valueOf("235478121"),
			Long.valueOf("351327239"), Long.valueOf("158317467"), Long.valueOf("201640762"), Long.valueOf("166215374"),
			Long.valueOf("171605249"), Long.valueOf("174745750"), Long.valueOf("304592969"), Long.valueOf("176150602"),
			Long.valueOf("180492138"), Long.valueOf("184469084"), Long.valueOf("360619686"), Long.valueOf("202105650"),
			Long.valueOf("255088025"), Long.valueOf("276296456"), Long.valueOf("251223395"), Long.valueOf("447901705"),
			Long.valueOf("295897758"), Long.valueOf("555816364"), Long.valueOf("307278606"), Long.valueOf("325532316"),
			Long.valueOf("293550691"), Long.valueOf("301448598"), Long.valueOf("324532841"), Long.valueOf("476389896"),
			Long.valueOf("333063217"), Long.valueOf("531384481"), Long.valueOf("338809605"), Long.valueOf("364961222"),
			Long.valueOf("337820623"), Long.valueOf("346350999"), Long.valueOf("870194086"), Long.valueOf("702989730"),
			Long.valueOf("620049247"), Long.valueOf("386574734"), Long.valueOf("435692479"), Long.valueOf("457193675"),
			Long.valueOf("589044018"), Long.valueOf("772434546"), Long.valueOf("657596058"), Long.valueOf("749350303"),
			Long.valueOf("680125425"), Long.valueOf("589448449"), Long.valueOf("594999289"), Long.valueOf("600829297"),
			Long.valueOf("988578156"), Long.valueOf("639901690"), Long.valueOf("625981439"), Long.valueOf("724395357"),
			Long.valueOf("670883840"), Long.valueOf("671872822"), Long.valueOf("676630228"), Long.valueOf("702781845"),
			Long.valueOf("684171622"), Long.valueOf("927269072"), Long.valueOf("1160087836"),
			Long.valueOf("1909438139"), Long.valueOf("822267213"), Long.valueOf("976023183"), Long.valueOf("843768409"),
			Long.valueOf("1279460888"), Long.valueOf("1660194805"), Long.valueOf("1265883129"),
			Long.valueOf("1297854261"), Long.valueOf("1234900979"), Long.valueOf("1184447738"),
			Long.valueOf("1190277746"), Long.valueOf("1195828586"), Long.valueOf("2162170051"),
			Long.valueOf("1316531918"), Long.valueOf("1296865279"), Long.valueOf("1355055462"),
			Long.valueOf("1302611667"), Long.valueOf("1342756662"), Long.valueOf("1836718064"),
			Long.valueOf("1979241895"), Long.valueOf("1981036901"), Long.valueOf("2533034408"),
			Long.valueOf("1506438835"), Long.valueOf("2589956441"), Long.valueOf("2487143025"),
			Long.valueOf("2500784108"), Long.valueOf("1666035622"), Long.valueOf("1819791592"),
			Long.valueOf("2028216147"), Long.valueOf("2450330867"), Long.valueOf("2374725484"),
			Long.valueOf("2386106332"), Long.valueOf("2482301999"), Long.valueOf("2419348717"),
			Long.valueOf("2380276324"), Long.valueOf("2849195497"), Long.valueOf("4165727415"),
			Long.valueOf("2599476946"), Long.valueOf("3647072523"), Long.valueOf("4319020063"),
			Long.valueOf("2645368329"), Long.valueOf("2809050502"), Long.valueOf("3370972809"),
			Long.valueOf("3172474457"), Long.valueOf("3960278796"), Long.valueOf("7491494520"),
			Long.valueOf("3848007739"), Long.valueOf("4105915781"), Long.valueOf("3485827214"),
			Long.valueOf("5336338522"), Long.valueOf("6914966283"), Long.valueOf("3694251769"),
			Long.valueOf("4194517076"), Long.valueOf("4402941631"), Long.valueOf("4755001808"),
			Long.valueOf("4979753270"), Long.valueOf("7360029594"), Long.valueOf("6027348847"),
			Long.valueOf("4799625041"), Long.valueOf("5025644653"), Long.valueOf("5494563826"),
			Long.valueOf("5244845275"), Long.valueOf("5408527448"), Long.valueOf("7048309960"),
			Long.valueOf("5454418831"), Long.valueOf("6016341138"), Long.valueOf("6543447266"),
			Long.valueOf("6658301671"), Long.valueOf("8363220427"), Long.valueOf("7180078983"),
			Long.valueOf("7333834953"), Long.valueOf("7591742995"), Long.valueOf("7888768845"),
			Long.valueOf("10400793497"), Long.valueOf("8097193400"), Long.valueOf("14250044666"),
			Long.valueOf("8597458707"), Long.valueOf("9439362351"), Long.valueOf("10224598545"),
			Long.valueOf("10254043872"), Long.valueOf("9779378311"), Long.valueOf("14513913936"),
			Long.valueOf("9825269694"), Long.valueOf("11041985791"), Long.valueOf("10270489928"),
			Long.valueOf("10653372723"), Long.valueOf("16286831066"), Long.valueOf("17862232923"),
			Long.valueOf("11470759969"), Long.valueOf("14051877538"), Long.valueOf("12559788404"),
			Long.valueOf("14640640666"), Long.valueOf("13838380654"), Long.valueOf("14771821978"),
			Long.valueOf("14925577948"), Long.valueOf("24914707433"), Long.valueOf("15480511840"),
			Long.valueOf("15985962245"), Long.valueOf("16694652107"), Long.valueOf("17536555751"),
			Long.valueOf("18036821058"), Long.valueOf("18376837018"), Long.valueOf("19218740662"),
			Long.valueOf("25167286659"), Long.valueOf("23213161127"), Long.valueOf("22830278332"),
			Long.valueOf("20095759622"), Long.valueOf("26242581947"), Long.valueOf("43291544451"),
			Long.valueOf("29007315720"), Long.valueOf("22124132692"), Long.valueOf("24030548373"),
			Long.valueOf("25309140623"), Long.valueOf("25522637507"), Long.valueOf("59277506696"),
			Long.valueOf("30757784223"), Long.valueOf("28479021320"), Long.valueOf("29697399926"),
			Long.valueOf("32175163947"), Long.valueOf("38693672967"), Long.valueOf("31466474085"),
			Long.valueOf("33857348858"), Long.valueOf("42937234054"), Long.valueOf("34231207858"),
			Long.valueOf("35573376809"), Long.valueOf("50552000965"), Long.valueOf("37595577680"),
			Long.valueOf("55249897667"), Long.valueOf("42219892314"), Long.valueOf("50831778130"),
			Long.valueOf("42926037954"), Long.valueOf("54001658827"), Long.valueOf("59753845365"),
			Long.valueOf("54316456343"), Long.valueOf("67700988687"), Long.valueOf("46154681065"),
			Long.valueOf("49339688996"), Long.valueOf("53788161943"), Long.valueOf("81630907021"),
			Long.valueOf("113065668639"), Long.valueOf("58176421246"), Long.valueOf("62336370178"),
			Long.valueOf("61163874011"), Long.valueOf("63641638032"), Long.valueOf("65323822943"),
			Long.valueOf("97253690397"), Long.valueOf("68088556716"), Long.valueOf("89091915119"),
			Long.valueOf("90823274476"), Long.valueOf("73168954489"), Long.valueOf("108249860897"),
			Long.valueOf("79815469994"), Long.valueOf("85145930268"), Long.valueOf("104089911965"),
			Long.valueOf("89080719019"), Long.valueOf("133412379659"), Long.valueOf("95494370061"),
			Long.valueOf("100471137408"), Long.valueOf("107318555076"), Long.valueOf("99942843008"),
			Long.valueOf("126957116432"), Long.valueOf("103127850939"), Long.valueOf("125978008210"),
			Long.valueOf("154799861510"), Long.valueOf("119340295257"), Long.valueOf("120512791424"),
			Long.valueOf("124805512043"), Long.valueOf("207789692484"), Long.valueOf("128965460975"),
			Long.valueOf("138492777432"), Long.valueOf("189235842233"), Long.valueOf("223638707700"),
			Long.valueOf("152984424483"), Long.valueOf("170638744470"), Long.valueOf("158314884757"),
			Long.valueOf("164961400262"), Long.valueOf("245811332508"), Long.valueOf("200413980416"),
			Long.valueOf("277957299546"), Long.valueOf("184575089080"), Long.valueOf("195437213069"),
			Long.valueOf("314777508326"), Long.valueOf("225276649451"), Long.valueOf("232124067119"),
			Long.valueOf("203070693947"), Long.valueOf("446225312924"), Long.valueOf("245318303467"),
			Long.valueOf("257833072689"), Long.valueOf("315950004493"), Long.valueOf("239853086681"),
			Long.valueOf("249478252399"), Long.valueOf("253770973018"), Long.valueOf("448596044016"),
			Long.valueOf("267458238407"), Long.valueOf("291477201915"), Long.valueOf("323276285019"),
			Long.valueOf("311299309240"), Long.valueOf("358728865173"), Long.valueOf("440267067097"),
			Long.valueOf("342889973837"), Long.valueOf("360398613331"), Long.valueOf("428347343398"),
			Long.valueOf("380012302149"), Long.valueOf("435194761066"), Long.valueOf("387645783027"),
			Long.valueOf("440755516536"), Long.valueOf("448388997414"), Long.valueOf("442923780628"),
			Long.valueOf("693027833755"), Long.valueOf("627856851738"), Long.valueOf("485171390148"),
			Long.valueOf("489331339080"), Long.valueOf("511604045707"), Long.valueOf("493624059699"),
			Long.valueOf("531330288596"), Long.valueOf("503249225417"), Long.valueOf("521229211425"),
			Long.valueOf("558935440322"), Long.valueOf("578757547647"), Long.valueOf("602776511155"),
			Long.valueOf("634575594259"), Long.valueOf("654189283077"), Long.valueOf("722902275986"),
			Long.valueOf("1053426447086"), Long.valueOf("703288587168"), Long.valueOf("748044396358"),
			Long.valueOf("1006400601573"), Long.valueOf("767658085176"), Long.valueOf("822840544093"),
			Long.valueOf("828401299563"), Long.valueOf("1681283298824"), Long.valueOf("891312778042"),
			Long.valueOf("928095170776"), Long.valueOf("988420615565"), Long.valueOf("974502729228"),
			Long.valueOf("978795449847"), Long.valueOf("1005228105406"), Long.valueOf("996873285116"),
			Long.valueOf("1034579514013"), Long.valueOf("1024478436842"), Long.valueOf("1124005722580"),
			Long.valueOf("1099986759072"), Long.valueOf("1137692987969"), Long.valueOf("1232946830724"),
			Long.valueOf("1237352105414"), Long.valueOf("1288764877336"), Long.valueOf("1421847368253"),
			Long.valueOf("2165447276190"), Long.valueOf("1515702481534"), Long.valueOf("1801635993940"),
			Long.valueOf("1570884940451"), Long.valueOf("2124259608766"), Long.valueOf("1902597900004"),
			Long.valueOf("1651241843656"), Long.valueOf("2234225390530"), Long.valueOf("1819407948818"),
			Long.valueOf("2009082243241"), Long.valueOf("1916515786341"), Long.valueOf("1953298179075"),
			Long.valueOf("1971376014344"), Long.valueOf("2158585236593"), Long.valueOf("2002101390522"),
			Long.valueOf("2021351721958"), Long.valueOf("2059057950855"), Long.valueOf("2332933589796"),
			Long.valueOf("2223992481652"), Long.valueOf("3109069002313"), Long.valueOf("2375045093383"),
			Long.valueOf("4167548666712"), Long.valueOf("2526116982750"), Long.valueOf("2710612245589"),
			Long.valueOf("2937549849787"), Long.valueOf("4508434790238"), Long.valueOf("4035861384470"),
			Long.valueOf("3372520934391"), Long.valueOf("3473482840455"), Long.valueOf("4528218373272"),
			Long.valueOf("3470649792474"), Long.valueOf("5475584230977"), Long.valueOf("3735923735159"),
			Long.valueOf("4335034980318"), Long.valueOf("3869813965416"), Long.valueOf("3887891800685"),
			Long.valueOf("4195368495996"), Long.valueOf("3973477404866"), Long.valueOf("4023453112480"),
			Long.valueOf("6673473584946"), Long.valueOf("8530403476314"), Long.valueOf("4283050432507"),
			Long.valueOf("4556926071448"), Long.valueOf("6658095525890"), Long.valueOf("7395974046871"),
			Long.valueOf("4901162076133"), Long.valueOf("5236729228339"), Long.valueOf("8564079757742"),
			Long.valueOf("6684089650455"), Long.valueOf("6961002962267"), Long.valueOf("6843170726865"),
			Long.valueOf("6846003774846"), Long.valueOf("6944132632929"), Long.valueOf("7861369205551"),
			Long.valueOf("7206573527633"), Long.valueOf("7340463757890"), Long.valueOf("7605737700575"),
			Long.valueOf("7623815535844"), Long.valueOf("9236197056451"), Long.valueOf("7757705766101"),
			Long.valueOf("14239144773736"), Long.valueOf("12418295276999"), Long.valueOf("7996930517346"),
			Long.valueOf("10137891304472"), Long.valueOf("8839976503955"), Long.valueOf("15229553236419"),
			Long.valueOf("12197732190606"), Long.valueOf("14602547574504"), Long.valueOf("11559257602023"),
			Long.valueOf("11747165850979"), Long.valueOf("11585251726588"), Long.valueOf("11920818878794"),
			Long.valueOf("13527260377320"), Long.valueOf("19102384927454"), Long.valueOf("24165461127978"),
			Long.valueOf("14946201458465"), Long.valueOf("13790136407775"), Long.valueOf("14150706160562"),
			Long.valueOf("14547037285523"), Long.valueOf("26569001437561"), Long.valueOf("15098169523991"),
			Long.valueOf("20587142354934"), Long.valueOf("22599478091850"), Long.valueOf("15754636283447"),
			Long.valueOf("16597682270056"), Long.valueOf("18134821821818"), Long.valueOf("22943131975811"),
			Long.valueOf("31924958229593"), Long.valueOf("18977867808427"), Long.valueOf("26974798325773"),
			Long.valueOf("23144509328611"), Long.valueOf("23306423453002"), Long.valueOf("25537302258754"),
			Long.valueOf("41077953797629"), Long.valueOf("31695851794047"), Long.valueOf("23506070605382"),
			Long.valueOf("30044370982456"), Long.valueOf("30748388430618"), Long.valueOf("28337173693298"),
			Long.valueOf("33889458105265"), Long.valueOf("29544772691222"), Long.valueOf("28888305931766"),
			Long.valueOf("28697743446085"), Long.valueOf("30852805807438"), Long.valueOf("32352318553503"),
			Long.valueOf("33232991345809"), Long.valueOf("34732504091874"), Long.valueOf("41640892427200"),
			Long.valueOf("35575550078483"), Long.valueOf("57675636067685"), Long.valueOf("41441245274820"),
			Long.valueOf("41920999784238"), Long.valueOf("64085797153247"), Long.valueOf("42483938413809"),
			Long.valueOf("52394376537148"), Long.valueOf("52851196144224"), Long.valueOf("46812494058384"),
			Long.valueOf("57233154052801"));
	static int rangeSize = 25;

	@Test
	public void test() {

		// Part one
		assertEquals(input.subList(0, 2), Arrays.asList(Long.valueOf("17"), Long.valueOf("14")));
		Long sum = Long.valueOf("123") + Long.valueOf("345");
		assertEquals(sum, Long.valueOf("468"));
		assertNotEquals(sum, Long.valueOf("467"));
		Long firstInvalid = getFirstInvalid();
		System.out.println(firstInvalid);

		// Part two
		List<Long> range = getRange(firstInvalid);
		Collections.sort(range);
		sum = range.get(0) + range.get(range.size()-1);
		System.out.println(sum);

	}

	private List<Long> getRange(Long firstInvalid) {
		List<Long> _return;
		Long sum;
		for (int i = 0; i < input.size()-1; i++) {
			_return = new ArrayList<Long>();
			_return.add(input.get(i));
			sum = input.get(i);
			if (sum > firstInvalid) {
				continue;
			}
			for (int j = i+1; j < input.size(); j++) {
				sum += input.get(j);
				_return.add(input.get(j));
				if (sum.equals(firstInvalid)) {
					return _return;
				}
				if (sum > firstInvalid) {
					break;
				}
			}
			
		}
		return null;
		
	}

	private Long getFirstInvalid() {
		for (int i = rangeSize; i < input.size(); i++) {
			if (!isValid(i)) {
				return input.get(i);
			}
		}
		return null;
	}

	private boolean isValid(int index) {
		if (index < rangeSize) {
			throw new IllegalArgumentException(index + " (index)  < " + rangeSize + " (rangeSize)");
		}
		if (index >= input.size()) {
			throw new IllegalArgumentException(index + " (index)  >= " + input.size() + " (input.size())");
		}
		List<Long> range = input.subList(index - rangeSize, index);
		Long number = input.get(index);

		for (int i = 0; i < range.size() - 1; i++) {
			for (int j = i + 1; j < range.size(); j++) {
				Long first = range.get(i);
				Long second = range.get(j);
				if (first.equals(second)) {
					continue;
				}
				Long sum = first + second;
				if (sum.equals(number)) {
					return true;
				}
			}
		}
		return false;
	}

}
