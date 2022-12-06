package y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class Day14 {

	static List<Instruction> input = Arrays.asList(
			new MaskInstruction("00000X110010111111X000100XX01010000X"),
			new MemoryInstruction(20690, 435), new MemoryInstruction(54036, 231), new MemoryInstruction(27099, 118644255), new MemoryInstruction(55683, 22299263),
			new MemoryInstruction(26119, 2279399), new MaskInstruction("00X000X0001X111111101X1111XX11X001XX"), new MemoryInstruction(42072, 1658073),
			new MemoryInstruction(63234, 2277), new MaskInstruction("1001X010011011111110101101X0XX11X010"), new MemoryInstruction(31090, 52291),
			new MemoryInstruction(31244, 377352406), new MemoryInstruction(10621, 18801757), new MemoryInstruction(31666, 5100853),
			new MaskInstruction("10X0110X11XX101XX1000011001001010100"), new MemoryInstruction(18680, 80608039), new MemoryInstruction(13197, 7957847),
			new MemoryInstruction(17080, 117501010), new MaskInstruction("1000110011111X11X1XXXX1X000X010011X1"), new MemoryInstruction(25308, 257586),
			new MemoryInstruction(14518, 62108102), new MemoryInstruction(21633, 1544993), new MemoryInstruction(36955, 1363),
			new MemoryInstruction(45764, 49755959), new MemoryInstruction(40967, 425), new MemoryInstruction(47858, 611686),
			new MaskInstruction("0010111010X0111111011X0110X0101010X1"), new MemoryInstruction(7451, 1208), new MemoryInstruction(31918, 769),
			new MemoryInstruction(29313, 1888678), new MemoryInstruction(52254, 32237487), new MaskInstruction("00X001001111X11111X010000X0110XX0X11"),
			new MemoryInstruction(61531, 15796066), new MemoryInstruction(305, 130785), new MemoryInstruction(25845, 197912), new MemoryInstruction(29251, 374061),
			new MemoryInstruction(37177, 17950), new MaskInstruction("100100X00110111111100110001X1X100X00"), new MemoryInstruction(40491, 66538375),
			new MemoryInstruction(42244, 240009051), new MemoryInstruction(18805, 33518831), new MemoryInstruction(17072, 518835559),
			new MaskInstruction("XX100100X1101X11010001X11001100XX1XX"), new MemoryInstruction(16935, 1124623), new MemoryInstruction(45248, 155461),
			new MemoryInstruction(37224, 5755511), new MaskInstruction("00X011101110101X10X1XXX1100X0001000X"), new MemoryInstruction(6440, 116801),
			new MemoryInstruction(193, 7318437), new MemoryInstruction(58568, 8082803), new MemoryInstruction(43695, 909697), new MemoryInstruction(29001, 27290),
			new MemoryInstruction(29210, 91241), new MaskInstruction("XXX01X001111111111101010000001XX1011"), new MemoryInstruction(21289, 354401446),
			new MemoryInstruction(33814, 1605382), new MemoryInstruction(16967, 242083755), new MemoryInstruction(60470, 22550),
			new MemoryInstruction(16485, 3945104), new MemoryInstruction(37687, 86474), new MemoryInstruction(51031, 5255),
			new MaskInstruction("00100100111101111100100X0X001XX10011"), new MemoryInstruction(34832, 191857526),
			new MemoryInstruction(30126, 180246093), new MemoryInstruction(310, 1895), new MemoryInstruction(49300, 117732),
			new MaskInstruction("00000100011011X11X10010XX1XX0X1101XX"), new MemoryInstruction(54544, 1368), new MemoryInstruction(30126, 596855),
			new MemoryInstruction(18483, 124319430), new MemoryInstruction(63246, 95337119), new MemoryInstruction(3917, 1620395),
			new MaskInstruction("00XX010001101111X110011X010100X10XX1"), new MemoryInstruction(31090, 203896198), new MemoryInstruction(36989, 203),
			new MemoryInstruction(8762, 372392), new MemoryInstruction(59728, 486751), new MaskInstruction("00101X00X11X11111X100010XXXX0011011X"),
			new MemoryInstruction(59728, 30591660), new MemoryInstruction(43720, 315507593), new MemoryInstruction(39732, 42157),
			new MemoryInstruction(3440, 242110717), new MemoryInstruction(36955, 871544), new MemoryInstruction(51251, 2489781),
			new MaskInstruction("00X00X0X01111111110000001100100X0011"), new MemoryInstruction(51149, 12451455), new MemoryInstruction(17566, 351620601),
			new MemoryInstruction(33842, 1119118), new MemoryInstruction(23677, 100601411), new MemoryInstruction(12826, 2474316),
			new MaskInstruction("00X00100XX1111111110111X1X010X10010X"), new MemoryInstruction(43163, 27012), new MemoryInstruction(53314, 2717910),
			new MemoryInstruction(20842, 239857), new MemoryInstruction(43816, 3173699), new MemoryInstruction(11343, 37315312),
			new MemoryInstruction(37493, 262038), new MemoryInstruction(25824, 13598271), new MaskInstruction("00X0X00001101X1X10100000110001110101"),
			new MemoryInstruction(39732, 1402), new MemoryInstruction(50014, 32437274), new MemoryInstruction(10770, 192187204),
			new MaskInstruction("000101110110X111111011010X11000000XX"), new MemoryInstruction(51283, 2490405), new MemoryInstruction(33814, 471881),
			new MemoryInstruction(15119, 3807095), new MaskInstruction("000X00XX0X10111111X0011XX11X10X00010"), new MemoryInstruction(10405, 46099021),
			new MemoryInstruction(42308, 1001), new MemoryInstruction(57329, 2310), new MaskInstruction("11X10110011XX100XX1010101100010X0000"),
			new MemoryInstruction(40240, 28185370), new MemoryInstruction(43296, 2212), new MemoryInstruction(15632, 3512122),
			new MemoryInstruction(61953, 2534700), new MemoryInstruction(58797, 258533), new MaskInstruction("00000X00011X1X11X1X001000010010X1110"),
			new MemoryInstruction(13671, 66116), new MemoryInstruction(5234, 46868488), new MemoryInstruction(48068, 259070), new MemoryInstruction(35833, 1904),
			new MaskInstruction("XX1101000XX01111X1100XX10X1000011000"), new MemoryInstruction(58276, 827), new MemoryInstruction(29197, 6552),
			new MemoryInstruction(21249, 173), new MemoryInstruction(5723, 4730123), new MemoryInstruction(59627, 3299104), new MemoryInstruction(17008, 74955518),
			new MaskInstruction("0X000100111111XX11001000000110X00100"), new MemoryInstruction(53231, 909153), new MemoryInstruction(28837, 1739162),
			new MemoryInstruction(21336, 3932), new MemoryInstruction(32899, 872661), new MemoryInstruction(29051, 228916),
			new MaskInstruction("0X1X11000111111X101111100000X10X00XX"), new MemoryInstruction(23121, 4940), new MemoryInstruction(64259, 339599819),
			new MemoryInstruction(268, 2533), new MemoryInstruction(5725, 1430), new MemoryInstruction(56946, 618),
			new MaskInstruction("001X1X0001111111101001111001X101XX10"), new MemoryInstruction(46780, 339675), new MemoryInstruction(57420, 10161),
			new MemoryInstruction(32105, 5534), new MaskInstruction("X1000X10011001111110110100X01010X011"), new MemoryInstruction(47922, 892051565),
			new MemoryInstruction(50583, 2962439), new MemoryInstruction(43673, 107), new MaskInstruction("000001001X11011111101X00XX1111100111"),
			new MemoryInstruction(17938, 29693823), new MemoryInstruction(27809, 17197), new MemoryInstruction(62755, 6590924), new MemoryInstruction(26483, 15837),
			new MemoryInstruction(5245, 486), new MemoryInstruction(8213, 1239), new MaskInstruction("0010X0000XX11XX1100010X0X11000101XX1"),
			new MemoryInstruction(3842, 3541), new MemoryInstruction(55663, 76779528), new MemoryInstruction(29851, 2801),
			new MaskInstruction("XX011X0001111111X1XX0000000001X00010"), new MemoryInstruction(20066, 97384), new MemoryInstruction(35212, 10209),
			new MemoryInstruction(15847, 499740), new MemoryInstruction(9349, 9638367), new MaskInstruction("001011X00XXX11X111100X1XX00010100X01"),
			new MemoryInstruction(52845, 1056563), new MemoryInstruction(30126, 13918626), new MemoryInstruction(17709, 25538089),
			new MemoryInstruction(1413, 459461), new MemoryInstruction(59577, 52944410), new MaskInstruction("X1011001X0XX111011100XX010101X01X010"),
			new MemoryInstruction(56449, 144), new MemoryInstruction(8753, 984864), new MemoryInstruction(23728, 173703761), new MemoryInstruction(34970, 28269),
			new MemoryInstruction(32500, 49931), new MaskInstruction("X0000100111X11111110X10X0101001100X1"), new MemoryInstruction(64582, 6646737),
			new MemoryInstruction(37177, 10), new MemoryInstruction(57474, 313623), new MemoryInstruction(17322, 147838906), new MemoryInstruction(28766, 15110001),
			new MemoryInstruction(49, 80836580), new MaskInstruction("001010000X11111X10X0X111X0XX00X100X1"), new MemoryInstruction(53163, 15243),
			new MemoryInstruction(61002, 406400), new MemoryInstruction(28930, 465647779), new MaskInstruction("0010000X11101111100010X00XX1XX01XX01"),
			new MemoryInstruction(1315, 625209), new MemoryInstruction(44187, 14395), new MaskInstruction("X0010XX0011011111110110X0101XX0X00XX"),
			new MemoryInstruction(31859, 95408), new MemoryInstruction(16534, 121119590), new MemoryInstruction(26550, 8188494),
			new MemoryInstruction(37302, 407378), new MaskInstruction("001XX1XXX1111X1110100001X00000111001"), new MemoryInstruction(37574, 31364),
			new MemoryInstruction(26443, 2676291), new MemoryInstruction(22192, 26966115), new MaskInstruction("001X010X001X011110001101XXX0X101XX11"),
			new MemoryInstruction(41368, 50472035), new MemoryInstruction(25252, 3850), new MemoryInstruction(21011, 7912441),
			new MemoryInstruction(55890, 2474497), new MaskInstruction("0010010000X11XX111X0101X100000101000"), new MemoryInstruction(57489, 10006848),
			new MemoryInstruction(7880, 30889), new MemoryInstruction(54742, 14408), new MaskInstruction("0010100X00X1X11X101000X1X00X001100XX"),
			new MemoryInstruction(28474, 137340532), new MemoryInstruction(57910, 3261), new MemoryInstruction(35212, 974067528),
			new MemoryInstruction(24595, 15641), new MaskInstruction("0X0X01X00110X11111X0X001X1011010X101"), new MemoryInstruction(1515, 4597),
			new MemoryInstruction(20626, 483632), new MemoryInstruction(50912, 101611112), new MemoryInstruction(62450, 463312),
			new MaskInstruction("00101X000X0111011110X10X10011X100001"), new MemoryInstruction(5378, 132014), new MemoryInstruction(13345, 2058543),
			new MemoryInstruction(42684, 2824), new MemoryInstruction(34576, 6385683), new MemoryInstruction(27201, 2519), new MemoryInstruction(9632, 202081),
			new MaskInstruction("X0X1010001X011X111100001001X01XX1XX1"), new MemoryInstruction(1538, 2389067), new MemoryInstruction(4972, 19131),
			new MemoryInstruction(23129, 256828081), new MemoryInstruction(17188, 185346747), new MemoryInstruction(44295, 143437003),
			new MemoryInstruction(44830, 5686), new MemoryInstruction(46528, 4177799), new MaskInstruction("X0010010001X1111110011X011XX11X1X011"),
			new MemoryInstruction(7033, 2748), new MemoryInstruction(2431, 17997007), new MemoryInstruction(13924, 90861), new MemoryInstruction(63656, 497878),
			new MemoryInstruction(61841, 891), new MemoryInstruction(10405, 6177), new MemoryInstruction(55811, 43078384),
			new MaskInstruction("X110XX0011111XX1X11001X000000001X000"), new MemoryInstruction(62283, 8553774), new MemoryInstruction(14788, 308418),
			new MemoryInstruction(5878, 2324), new MaskInstruction("0000010001101111101XX1X00001001101XX"), new MemoryInstruction(23816, 69720),
			new MemoryInstruction(29524, 197631), new MaskInstruction("10X100100011X111110011010X0X10X10XX1"), new MemoryInstruction(5288, 1072),
			new MemoryInstruction(34681, 1902), new MemoryInstruction(47529, 1012160), new MemoryInstruction(42117, 232642695),
			new MemoryInstruction(7153, 420427964), new MemoryInstruction(23129, 10261), new MemoryInstruction(24545, 1661292),
			new MaskInstruction("X1011X010XX0X1X1X010001001001X001111"), new MemoryInstruction(3984, 17460969), new MemoryInstruction(43208, 1626),
			new MemoryInstruction(12288, 3244), new MemoryInstruction(1261, 685777140), new MemoryInstruction(35662, 3875), new MemoryInstruction(13197, 807702837),
			new MemoryInstruction(8450, 39850899), new MaskInstruction("001X0X00011X11111000000110010X0XXX1X"), new MemoryInstruction(35167, 3384),
			new MemoryInstruction(1969, 3362919), new MemoryInstruction(4732, 99083530), new MemoryInstruction(58162, 1382314),
			new MaskInstruction("10000X10011011X11010000X11X1X0010100"), new MemoryInstruction(50583, 4112), new MemoryInstruction(4097, 907),
			new MemoryInstruction(45785, 1275731), new MemoryInstruction(31108, 7733), new MemoryInstruction(50267, 2625942),
			new MaskInstruction("X0010X1001X011111110110011000111X0X0"), new MemoryInstruction(35870, 1100551), new MemoryInstruction(8514, 8042956),
			new MemoryInstruction(10848, 96032), new MemoryInstruction(44678, 213384), new MemoryInstruction(25743, 3586812), new MemoryInstruction(34074, 991022),
			new MaskInstruction("X010X1001111X1111110010X000011000011"), new MemoryInstruction(61953, 3703), new MemoryInstruction(41415, 250960289),
			new MemoryInstruction(24262, 14129393), new MaskInstruction("000X010XX11011X111100100110X001000X0"), new MemoryInstruction(51393, 320156165),
			new MemoryInstruction(27955, 21751009), new MemoryInstruction(61468, 8941693), new MemoryInstruction(24188, 176466079),
			new MemoryInstruction(10717, 2950), new MaskInstruction("0010XX00X11110101000X0110000X101X100"), new MemoryInstruction(37149, 18981413),
			new MemoryInstruction(12384, 479738), new MemoryInstruction(17072, 5196), new MemoryInstruction(59325, 170080), new MemoryInstruction(3269, 86268393),
			new MemoryInstruction(48598, 18530), new MemoryInstruction(11287, 4082), new MaskInstruction("0X101100X11X11111X10X0X01000X1110010"),
			new MemoryInstruction(47267, 12410), new MemoryInstruction(8609, 6923289), new MemoryInstruction(28364, 23091829), new MemoryInstruction(63780, 858),
			new MemoryInstruction(21558, 48929393), new MemoryInstruction(46110, 74033138), new MaskInstruction("001011X0011111111010X10100X10X000011"),
			new MemoryInstruction(30364, 14013071), new MemoryInstruction(23121, 5777), new MemoryInstruction(54108, 11707710),
			new MaskInstruction("0010XX00X11X11111XX010X00X000X010011"), new MemoryInstruction(29453, 3480476), new MemoryInstruction(7516, 869816189),
			new MemoryInstruction(57136, 130673464), new MemoryInstruction(8609, 2000), new MemoryInstruction(45543, 1014823), new MemoryInstruction(3249, 75),
			new MemoryInstruction(14460, 18422415), new MaskInstruction("000X011000111X1X111010X0X10X11X10011"), new MemoryInstruction(14556, 7853751),
			new MemoryInstruction(29755, 535169084), new MemoryInstruction(24262, 4027), new MemoryInstruction(34051, 13187123),
			new MaskInstruction("000X011X0X1X1111X110000000XX0000X11X"), new MemoryInstruction(13879, 2383), new MemoryInstruction(57329, 749),
			new MemoryInstruction(54544, 3055190), new MaskInstruction("00110XX00111111X10010001XX1000101000"), new MemoryInstruction(4852, 429814346),
			new MemoryInstruction(55439, 7610), new MemoryInstruction(31685, 811508716), new MemoryInstruction(38296, 185763), new MemoryInstruction(16482, 3668),
			new MemoryInstruction(47529, 3803), new MaskInstruction("X01011000111111X1011010101101X011X01"), new MemoryInstruction(58499, 851439),
			new MemoryInstruction(38516, 3082), new MemoryInstruction(32500, 364520), new MaskInstruction("001X110001111X10X000001011X001000101"),
			new MemoryInstruction(44653, 157371860), new MemoryInstruction(2226, 58088617), new MemoryInstruction(10098, 67459), new MemoryInstruction(45739, 3994),
			new MemoryInstruction(4180, 206930963), new MaskInstruction("001XX0000XX1111010X00011000001011011"), new MemoryInstruction(53876, 843104),
			new MemoryInstruction(56118, 1019), new MemoryInstruction(39503, 6758), new MemoryInstruction(24134, 9483199), new MemoryInstruction(25914, 26956),
			new MemoryInstruction(10098, 63837172), new MemoryInstruction(40642, 2366588), new MaskInstruction("0XX10X1001X1111X1X101010010XX01000X0"),
			new MemoryInstruction(16432, 17158914), new MemoryInstruction(29927, 9292527), new MemoryInstruction(57922, 24395252),
			new MemoryInstruction(48327, 253), new MemoryInstruction(15450, 496726), new MemoryInstruction(57027, 518857449),
			new MaskInstruction("0101XXX0011X11111110X0XX01X010000111"), new MemoryInstruction(39393, 570), new MemoryInstruction(38893, 21253926),
			new MaskInstruction("0010010X01XX1X1101100000010100110101"), new MemoryInstruction(64325, 416581774), new MemoryInstruction(26376, 1666947),
			new MemoryInstruction(6276, 90042), new MaskInstruction("0X00001101101111X11X010010XX1X1XX01X"), new MemoryInstruction(20354, 2180),
			new MemoryInstruction(50761, 7237731), new MemoryInstruction(54710, 5718), new MemoryInstruction(43883, 2618938),
			new MemoryInstruction(59235, 22130448), new MemoryInstruction(59325, 14410783), new MaskInstruction("00000001X0101X11X110101001X11100X0X1"),
			new MemoryInstruction(24262, 10756242), new MemoryInstruction(59282, 296121), new MemoryInstruction(15931, 49), new MemoryInstruction(44067, 339152264),
			new MemoryInstruction(22192, 2750756), new MemoryInstruction(897, 639), new MaskInstruction("001011X011111X1110X01X01X0XX0101X101"),
			new MemoryInstruction(21410, 5056), new MemoryInstruction(43472, 198924166), new MemoryInstruction(50343, 5363196), new MemoryInstruction(7486, 773744),
			new MemoryInstruction(49418, 77311216), new MaskInstruction("001X0000011011111X00000X1011X1X01XX0"), new MemoryInstruction(19633, 5522082),
			new MemoryInstruction(4682, 51724569), new MemoryInstruction(36252, 260), new MaskInstruction("001011X0111X1X1110XX000X100001X100X0"),
			new MemoryInstruction(34373, 803), new MemoryInstruction(61841, 25585959), new MemoryInstruction(29051, 2011), new MemoryInstruction(53885, 4255251),
			new MemoryInstruction(55135, 49781551), new MemoryInstruction(11748, 5712), new MaskInstruction("000001000X1011111110X11X0XX11101100X"),
			new MemoryInstruction(28073, 257781932), new MemoryInstruction(32292, 7788), new MemoryInstruction(47529, 21491591), new MemoryInstruction(26354, 3991),
			new MemoryInstruction(46496, 225777), new MemoryInstruction(19054, 6818), new MemoryInstruction(46391, 1804050),
			new MaskInstruction("0X01X1110011111101X011X1X10010011100"), new MemoryInstruction(8848, 3301953), new MemoryInstruction(21325, 828483041),
			new MemoryInstruction(35954, 393891988), new MaskInstruction("001010XX11X0X11X1010001000001111X1X1"), new MemoryInstruction(14556, 48978),
			new MemoryInstruction(17078, 3023995), new MemoryInstruction(41895, 1263), new MemoryInstruction(26354, 982), new MemoryInstruction(47494, 9997),
			new MemoryInstruction(42458, 139205796), new MaskInstruction("01011X0X01XX11X11X10001X010X10001100"), new MemoryInstruction(30326, 230268),
			new MemoryInstruction(13671, 406), new MemoryInstruction(13219, 816366), new MaskInstruction("00X0111000101101XX10X010X100X0101X01"),
			new MemoryInstruction(9172, 5603), new MemoryInstruction(10540, 399), new MemoryInstruction(6994, 2969), new MemoryInstruction(41827, 157730),
			new MemoryInstruction(57564, 713362), new MemoryInstruction(16823, 335722), new MemoryInstruction(38893, 724),
			new MaskInstruction("X1X10110011111X01X101X111101X0X1X000"), new MemoryInstruction(5336, 623), new MemoryInstruction(31859, 88241437),
			new MemoryInstruction(7282, 19257561), new MemoryInstruction(37684, 5467271), new MemoryInstruction(50354, 2120264),
			new MaskInstruction("0000X100111X1111X1X001X0000X00110001"), new MemoryInstruction(61468, 9124391), new MemoryInstruction(35212, 23096803),
			new MemoryInstruction(9949, 9454), new MemoryInstruction(1331, 995), new MemoryInstruction(39238, 74423),
			new MaskInstruction("00100100111X1111111011X1XX010X1X0010"), new MemoryInstruction(39294, 114968517), new MemoryInstruction(23155, 1708),
			new MemoryInstruction(29927, 7693420), new MemoryInstruction(26742, 2017), new MaskInstruction("001001001X101111X1X0XX01100X0X0101XX"),
			new MemoryInstruction(29056, 30646), new MemoryInstruction(59210, 113022), new MemoryInstruction(43000, 144138476), new MemoryInstruction(35167, 721),
			new MemoryInstruction(30809, 507151422), new MaskInstruction("0001X0100101111010X0100000X0X0100010"), new MemoryInstruction(34554, 49221),
			new MemoryInstruction(7437, 62877), new MemoryInstruction(59828, 184498), new MemoryInstruction(45586, 20089049),
			new MemoryInstruction(48248, 98197865), new MemoryInstruction(44772, 113026522), new MaskInstruction("00X0X000111X11111010X10X1011010X0X01"),
			new MemoryInstruction(40499, 11427785), new MemoryInstruction(60906, 496319403), new MemoryInstruction(55126, 270707060),
			new MaskInstruction("001XXXX10011X1111000110X0X000XX10001"), new MemoryInstruction(17938, 535040), new MemoryInstruction(59138, 250862772),
			new MemoryInstruction(8507, 41576622), new MemoryInstruction(14146, 1026), new MemoryInstruction(32774, 2975), new MemoryInstruction(39952, 440004),
			new MemoryInstruction(13671, 2552), new MaskInstruction("00000100X110111X111001X0X10X0XX100X1"), new MemoryInstruction(43163, 1156),
			new MemoryInstruction(35680, 6039549), new MemoryInstruction(15378, 657), new MemoryInstruction(52635, 15396), new MemoryInstruction(25926, 210),
			new MemoryInstruction(28827, 206264701), new MaskInstruction("001X100X001X1111100011X1XX1000100X01"), new MemoryInstruction(5753, 1286),
			new MemoryInstruction(6271, 6375), new MemoryInstruction(47122, 5264524), new MaskInstruction("0X01100XXX10111X1X100X10000X1000001X"),
			new MemoryInstruction(51736, 2480), new MemoryInstruction(16722, 663122146), new MemoryInstruction(18483, 119830),
			new MemoryInstruction(13423, 136699070), new MemoryInstruction(65442, 23484946), new MemoryInstruction(50742, 716),
			new MemoryInstruction(14788, 129808), new MaskInstruction("100100000X101111111001000101X00X01X0"), new MemoryInstruction(35279, 11923915),
			new MemoryInstruction(12886, 216539704), new MemoryInstruction(29197, 258471), new MemoryInstruction(34051, 24342647),
			new MemoryInstruction(34556, 219870381), new MaskInstruction("0010X100011011X11110X1101X010X1001X1"), new MemoryInstruction(17497, 684),
			new MemoryInstruction(8762, 255129), new MemoryInstruction(44486, 49869056), new MemoryInstruction(35680, 210517), new MemoryInstruction(18805, 17289),
			new MaskInstruction("001011101X1011111X01111010001000XX01"), new MemoryInstruction(31918, 89826257), new MemoryInstruction(31090, 57962),
			new MemoryInstruction(53807, 2817), new MemoryInstruction(12784, 2137), new MemoryInstruction(31369, 64658),
			new MaskInstruction("X000X1X0011011111010X0X111XX00X1X100"), new MemoryInstruction(63989, 3267), new MemoryInstruction(23677, 2600),
			new MemoryInstruction(42781, 125518), new MemoryInstruction(37480, 3172), new MemoryInstruction(23573, 8287963),
			new MaskInstruction("0010110001111XXX10XX0X1X0X0000010100"), new MemoryInstruction(1654, 317418946), new MemoryInstruction(46425, 459126),
			new MemoryInstruction(31666, 9190945), new MemoryInstruction(12079, 168185843), new MemoryInstruction(39256, 178728),
			new MemoryInstruction(52287, 59458806), new MaskInstruction("000001000110111X1010X00000X100110X00"), new MemoryInstruction(55820, 2071),
			new MemoryInstruction(33429, 821261571), new MemoryInstruction(16244, 3215), new MaskInstruction("100110100110X1111110X01101X1XX10X011"),
			new MemoryInstruction(47796, 98), new MemoryInstruction(31040, 1031334), new MemoryInstruction(9622, 1580517),
			new MaskInstruction("00100X001110XX11110000011101X0011001"), new MemoryInstruction(42781, 312404), new MemoryInstruction(39942, 123252858),
			new MemoryInstruction(20867, 1356), new MemoryInstruction(14667, 576), new MemoryInstruction(35502, 298476332), new MemoryInstruction(53427, 365745),
			new MaskInstruction("00010X100110111111100010XX00XX000100"), new MemoryInstruction(53876, 603517), new MemoryInstruction(10405, 16459102),
			new MemoryInstruction(45543, 4443), new MemoryInstruction(41543, 1411), new MemoryInstruction(62450, 6470215),
			new MaskInstruction("100X110X11111X110X0010000X0X10001X10"), new MemoryInstruction(5336, 650575), new MemoryInstruction(50124, 3080229),
			new MemoryInstruction(51618, 156), new MemoryInstruction(42185, 1366), new MaskInstruction("00X0X1101110101110XX1011X001001110X0"),
			new MemoryInstruction(30736, 333574460), new MemoryInstruction(13675, 8643742), new MemoryInstruction(12826, 453315),
			new MaskInstruction("000X00100X1X1X111XX0101X0X100111000X"), new MemoryInstruction(54995, 183737953), new MemoryInstruction(63234, 679),
			new MemoryInstruction(61488, 337), new MaskInstruction("0XX11111X0111X1X01001111X00X11001XX1"), new MemoryInstruction(10621, 122118726),
			new MemoryInstruction(6109, 9210), new MemoryInstruction(15688, 184799), new MemoryInstruction(25564, 367237),
			new MaskInstruction("00110000011X1111100X000X00XXX1XX1000"), new MemoryInstruction(17938, 33020705), new MemoryInstruction(2666, 88651117),
			new MemoryInstruction(21482, 161753));
	static List<Instruction> test1 = Arrays.asList(
			new MaskInstruction("XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X"), 
			new MemoryInstruction(8,11),
			new MemoryInstruction(7,101),
			new MemoryInstruction(8,0));
	static List<Instruction> test2 = Arrays.asList(
			new MaskInstruction("000000000000000000000000000000X1001X"), 
			new MemoryInstruction(42,100),
			new MaskInstruction("00000000000000000000000000000000X0XX"), 
			new MemoryInstruction(26,1));

	
	@Test
	public void test() {
		
		// Part one
		Memory memory = new Memory();
		memory.processInstructions(test1);
		assertEquals(165, memory.getSum());
		
//		memory = new Memory();
//		memory.processInstructions(input);
//		assertEquals(4297467072083L, memory.getSum());
		
		// Part two
		memory = new Memory();
		memory.processInstructions2(test2);
		assertEquals(208, memory.getSum());
		assertTrue(memory.isX('X'));;
		assertEquals(5, memory.binaryToInt("101"));
		assertEquals(34359738368L, memory.binaryToInt("100000000000000000000000000000000000"));
		assertEquals(68719476735L, memory.binaryToInt("111111111111111111111111111111111111"));
		assertNotEquals(3, memory.binaryToInt("101"));
		assertEquals(5, memory.binaryToInt("0101"));
		System.out.println(memory.getFloatingAddresses("1X1"));
		System.out.println(memory.getFloatingAddresses("XX1"));
		System.out.println(memory.getFloatingAddresses("XXX"));
		System.out.println(memory.getFloatingAddresses("10000000000000000000000000000000000X"));		
		
		memory = new Memory();
		memory.processInstructions2(input);
		assertEquals(5030603328768L, memory.getSum());
		
	}

	static interface Instruction {

	}

	static class MaskInstruction implements Instruction {
		String value;

		MaskInstruction(String value) {
			this.value = value;
		}

	}

	static class MemoryInstruction implements Instruction {
		long address;
		long value;

		MemoryInstruction(int address, int value) {
			this.address = address;
			this.value = value;
		}
	}
	
	class Memory extends HashMap<Long, Long> {

		private static final long serialVersionUID = -3518654981152441926L;

		public void processInstruction(MemoryInstruction instruction, String mask) {
			String value = Integer.toBinaryString((int) instruction.value);
			
			while (value.length() < mask.length()) {
				value = "0" + value;
			}
			
			for (int i = 0; i < mask.length(); i++) {
				int esp = mask.length() - 1 - i;
				if (isZero(mask.charAt(i))) {
					// 0
					if (isOne(value.charAt(i))) {
						instruction.value -= Math.pow(2, esp);
					}
				} 
				if (isOne(mask.charAt(i))) {
					// 1
					if (isZero(value.charAt(i))) {
						instruction.value += Math.pow(2, esp);
					}
				}
			}
			
			this.put(instruction.address, instruction.value);
		}

		public void processInstruction2(MemoryInstruction instruction, String mask) {
			String address = Long.toBinaryString(instruction.address);
			
			while (address.length() < mask.length()) {
				address = "0" + address;
			}
			
			for (int i = 0; i < mask.length(); i++) {
				if (!isZero(mask.charAt(i))) {
					// X, 1
					address = address.substring(0, i) + mask.charAt(i) + address.substring(i+1);
				} 
			}
			List<Long> addresses = getFloatingAddresses(address);
			
			for (Long key : addresses) {
				this.put(key, instruction.value);
			}
		}

		private List<Long> getFloatingAddresses(String address) {
			List<Long> result = new ArrayList<Long>();
			int i = 0;
			while(i < address.length() && !isX(address.charAt(i))) {
				i++;
			}
			if (i == address.length()) {
				return Arrays.asList(binaryToInt(address));				
			}
			String address1 = address.substring(0, i) + "0" + address.substring(i+1);
			String address2 = address.substring(0, i) + "1" + address.substring(i+1);
			result.addAll(getFloatingAddresses(address1));
			result.addAll(getFloatingAddresses(address2));
			return result;
		}

		private long binaryToInt(String binary) {
			long value = 0;
			for (int i = binary.length() - 1; i > -1; i--) {
				char charAt = binary.charAt(i);
				if (isOne(charAt)) {
					value += Math.pow(2, binary.length() - i - 1);
				}
			}	
			return value;
		}
		
		public void processInstructions(List<Instruction> instructions) {
			String mask = "";
			for (Instruction instruction : instructions) {
				if (instruction instanceof MaskInstruction) {
					mask = ((MaskInstruction) instruction).value;
				} else {
					this.processInstruction((MemoryInstruction) instruction, mask);
				}
			}
		}

		public void processInstructions2(List<Instruction> instructions) {
			String mask = "";
			for (Instruction instruction : instructions) {
				if (instruction instanceof MaskInstruction) {
					mask = ((MaskInstruction) instruction).value;
				} else {
					this.processInstruction2((MemoryInstruction) instruction, mask);
				}
			}
		}
		
		private boolean isZero(char c) {
			return 48 == c;
		}
		
		private boolean isOne(char c) {
			return 49 == c;
		}
		
		private boolean isX(char c) {
			return 88 == c;
		}
		
		public long getSum() {
			long sum = 0;
			for (Map.Entry<Long, Long> entry : this.entrySet()) {
				sum += entry.getValue();
			}
			return sum;
		}
		
	}
	
}
