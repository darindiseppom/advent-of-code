package mdrd.advent.of.code.y2020;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.jupiter.api.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.BoundType;
import com.google.common.collect.Iterables;
import com.google.common.collect.Range;

public class Day16 {
	// 3539290312643
	// 1553863070173 * 15 too high - escludo 2, 19
	// 1425709414901 * 5 not
	// 849017966177  * 20 not
	// 1169402104357 * 1	not
	// 16019206909

	private static final String START_WITH = "departure";
	static Ticket myTicket = new Ticket(73,167,113,61,89,59,191,103,67,83,163,109,101,71,97,151,107,79,157,53);
	static List<Field> fields = Arrays.asList(
			new Field("departure location", Range.range(45, BoundType.CLOSED, 609, BoundType.CLOSED),
					Range.range(616, BoundType.CLOSED, 954, BoundType.CLOSED), myTicket.size()),
			new Field("departure station", Range.range(32, BoundType.CLOSED, 194, BoundType.CLOSED),
					Range.range(211, BoundType.CLOSED, 972, BoundType.CLOSED), myTicket.size()),
			new Field("departure platform", Range.range(35, BoundType.CLOSED, 732, BoundType.CLOSED),
					Range.range(744, BoundType.CLOSED, 970, BoundType.CLOSED), myTicket.size()),
			new Field("departure track", Range.range(40, BoundType.CLOSED, 626, BoundType.CLOSED),
					Range.range(651, BoundType.CLOSED, 952, BoundType.CLOSED), myTicket.size()),
			new Field("departure date", Range.range(44, BoundType.CLOSED, 170, BoundType.CLOSED),
					Range.range(184, BoundType.CLOSED, 962, BoundType.CLOSED), myTicket.size()),
			new Field("departure time", Range.range(49, BoundType.CLOSED, 528, BoundType.CLOSED),
					Range.range(538, BoundType.CLOSED, 954, BoundType.CLOSED), myTicket.size()),
			new Field("arrival location", Range.range(36, BoundType.CLOSED, 448, BoundType.CLOSED),
					Range.range(464, BoundType.CLOSED, 956, BoundType.CLOSED), myTicket.size()),
			new Field("arrival station", Range.range(48, BoundType.CLOSED, 356, BoundType.CLOSED),
					Range.range(373, BoundType.CLOSED, 972, BoundType.CLOSED), myTicket.size()),
			new Field("arrival platform", Range.range(25, BoundType.CLOSED, 118, BoundType.CLOSED),
					Range.range(132, BoundType.CLOSED, 954, BoundType.CLOSED), myTicket.size()),
			new Field("arrival track", Range.range(43, BoundType.CLOSED, 703, BoundType.CLOSED),
					Range.range(719, BoundType.CLOSED, 965, BoundType.CLOSED), myTicket.size()),
			new Field("class", Range.range(29, BoundType.CLOSED, 822, BoundType.CLOSED),
					Range.range(828, BoundType.CLOSED, 961, BoundType.CLOSED), myTicket.size()),
			new Field("duration", Range.range(25, BoundType.CLOSED, 131, BoundType.CLOSED),
					Range.range(151, BoundType.CLOSED, 967, BoundType.CLOSED), myTicket.size()),
			new Field("price", Range.range(44, BoundType.CLOSED, 784, BoundType.CLOSED),
					Range.range(794, BoundType.CLOSED, 958, BoundType.CLOSED), myTicket.size()),
			new Field("route", Range.range(25, BoundType.CLOSED, 498, BoundType.CLOSED),
					Range.range(511, BoundType.CLOSED, 951, BoundType.CLOSED), myTicket.size()),
			new Field("row", Range.range(44, BoundType.CLOSED, 905, BoundType.CLOSED),
					Range.range(916, BoundType.CLOSED, 973, BoundType.CLOSED), myTicket.size()),
			new Field("seat", Range.range(26, BoundType.CLOSED, 756, BoundType.CLOSED),
					Range.range(777, BoundType.CLOSED, 960, BoundType.CLOSED), myTicket.size()),
			new Field("train", Range.range(36, BoundType.CLOSED, 803, BoundType.CLOSED),
					Range.range(819, BoundType.CLOSED, 954, BoundType.CLOSED), myTicket.size()),
			new Field("type", Range.range(33, BoundType.CLOSED, 318, BoundType.CLOSED),
					Range.range(335, BoundType.CLOSED, 967, BoundType.CLOSED), myTicket.size()),
			new Field("wagon", Range.range(46, BoundType.CLOSED, 558, BoundType.CLOSED),
					Range.range(570, BoundType.CLOSED, 969, BoundType.CLOSED), myTicket.size()),
			new Field("zone", Range.range(47, BoundType.CLOSED, 249, BoundType.CLOSED),
					Range.range(265, BoundType.CLOSED, 972, BoundType.CLOSED), myTicket.size()));
	static List<Ticket> nearbyTickets = Arrays.asList(
			new Ticket(852,748,166,696,714,108,222,229,152,731,513,879,551,837,291,384,156,72,78,685),
					new Ticket(617,653,290,113,846,782,621,607,231,117,411,920,442,249,434,597,528,702,201,684),
					new Ticket(385,424,169,712,795,477,167,935,838,445,515,304,108,229,194,304,930,655,470,389),
					new Ticket(479,117,603,349,748,540,834,406,882,502,841,296,470,476,523,50,62,673,228,100),
					new Ticket(943,616,662,858,840,616,156,152,943,284,588,861,582,215,747,392,374,826,895,730),
					new Ticket(430,83,846,100,267,447,159,90,478,160,985,164,513,424,802,72,798,420,493,668),
					new Ticket(235,668,884,544,660,212,114,875,343,861,190,904,676,585,191,484,825,835,284,803),
					new Ticket(164,691,481,839,421,868,897,615,373,835,949,664,154,316,870,414,306,719,446,654),
					new Ticket(427,695,216,218,834,949,497,395,599,585,99,156,721,515,696,862,429,659,645,307),
					new Ticket(438,782,442,72,703,865,903,471,112,623,58,276,481,832,141,341,777,721,468,344),
					new Ticket(555,653,944,292,870,94,696,926,544,830,424,343,61,51,119,584,444,296,700,572),
					new Ticket(350,232,822,454,284,477,294,70,896,474,238,352,754,157,619,447,271,71,834,402),
					new Ticket(56,410,604,157,579,266,483,826,92,266,819,920,658,888,473,311,403,431,672,853),
					new Ticket(353,10,862,588,837,547,686,104,79,877,158,676,497,894,380,821,99,246,69,663),
					new Ticket(317,609,343,863,900,70,62,163,416,100,83,874,834,687,237,746,938,146,215,654),
					new Ticket(538,235,384,313,604,573,63,883,866,92,728,588,409,282,798,349,429,800,454,622),
					new Ticket(88,512,392,70,229,686,887,643,727,318,858,349,96,274,723,475,393,97,290,605),
					new Ticket(860,526,514,898,935,85,856,580,297,826,466,429,584,483,928,65,79,225,385,490),
					new Ticket(478,399,152,571,240,728,467,930,496,878,285,675,779,5,72,336,442,411,794,939),
					new Ticket(678,231,165,54,100,596,663,469,161,835,293,699,475,229,572,282,624,847,892,990),
					new Ticket(699,730,307,901,853,477,482,597,214,663,829,486,64,699,814,919,194,311,868,554),
					new Ticket(513,579,845,113,185,888,555,579,353,248,722,245,245,482,763,412,512,574,539,626),
					new Ticket(456,749,526,93,678,282,585,308,245,485,938,616,867,114,239,446,727,885,376,617),
					new Ticket(936,835,988,56,942,822,839,520,185,878,84,523,293,301,406,557,156,745,926,849),
					new Ticket(423,85,476,928,985,186,551,278,424,86,744,744,434,160,435,77,667,386,685,301),
					new Ticket(90,167,540,448,889,919,894,378,237,533,186,408,86,900,498,384,217,184,244,621),
					new Ticket(115,394,587,752,558,675,489,932,838,596,941,728,485,925,111,693,571,639,684,546),
					new Ticket(943,605,192,609,347,388,480,439,448,584,486,695,229,783,614,488,237,854,558,844),
					new Ticket(411,215,396,674,829,314,923,846,94,63,690,572,721,64,4,189,421,945,845,300),
					new Ticket(515,379,934,841,350,443,226,231,93,859,819,69,112,84,437,825,870,916,831,431),
					new Ticket(614,473,583,476,586,703,419,184,683,660,298,949,858,682,931,356,340,271,412,523),
					new Ticket(892,413,849,194,248,341,192,60,442,381,887,243,185,473,588,790,78,666,110,336),
					new Ticket(556,97,930,495,380,491,726,419,166,60,933,133,942,419,290,80,422,946,185,472),
					new Ticket(583,415,947,719,384,697,544,919,356,544,290,310,790,920,749,777,492,50,617,580),
					new Ticket(407,65,665,78,507,269,89,874,901,314,622,698,112,308,927,656,832,226,307,783),
					new Ticket(994,876,489,748,70,662,216,249,581,64,442,683,696,280,118,274,836,310,399,681),
					new Ticket(303,94,602,827,355,245,99,155,313,444,820,651,310,702,838,943,92,60,386,699),
					new Ticket(211,570,889,698,848,934,187,105,470,907,99,349,294,724,155,573,665,63,929,277),
					new Ticket(374,63,287,107,517,577,819,721,86,673,581,354,511,94,110,169,703,730,613,756),
					new Ticket(265,925,881,780,238,91,51,867,891,231,174,67,898,236,474,433,520,658,932,391),
					new Ticket(84,779,860,82,212,858,749,223,152,225,118,220,306,471,380,61,698,4,88,730),
					new Ticket(931,902,981,492,594,60,335,166,301,233,274,797,666,236,191,217,924,310,107,673),
					new Ticket(861,158,884,670,486,752,944,249,928,301,269,218,935,878,289,186,379,712,881,314),
					new Ticket(95,81,672,616,409,184,306,436,871,355,422,821,877,676,716,408,187,848,520,687),
					new Ticket(902,311,189,799,246,341,474,477,544,89,476,226,666,483,272,830,531,153,55,51),
					new Ticket(947,220,594,212,673,593,437,820,899,855,884,667,934,440,503,50,947,465,118,592),
					new Ticket(194,724,887,730,934,465,841,427,693,487,526,236,538,468,828,652,175,386,781,90),
					new Ticket(473,285,392,249,339,748,796,898,481,237,901,152,883,665,94,442,791,624,229,606),
					new Ticket(802,346,724,949,99,659,213,58,263,722,600,622,924,947,163,877,160,723,348,494),
					new Ticket(801,268,937,11,60,265,118,929,109,152,470,832,697,110,161,396,604,101,97,99),
					new Ticket(316,924,302,190,386,675,938,62,681,724,777,234,199,445,288,466,480,869,97,244),
					new Ticket(512,294,587,690,420,102,512,929,876,786,754,337,544,285,784,81,575,425,298,376),
					new Ticket(479,652,918,435,773,492,618,936,298,99,82,477,780,466,523,945,98,511,162,755),
					new Ticket(673,472,653,427,652,431,551,260,409,440,351,778,75,309,747,889,726,169,221,890),
					new Ticket(512,376,753,527,224,554,660,871,311,296,833,400,379,192,696,294,410,741,606,664),
					new Ticket(923,348,343,297,304,225,827,83,876,672,219,722,550,600,389,820,229,673,297,544),
					new Ticket(689,492,515,299,466,920,387,239,307,405,375,867,226,302,877,461,477,421,519,702),
					new Ticket(73,524,544,243,58,495,884,164,992,97,220,434,538,583,51,218,719,247,280,407),
					new Ticket(277,552,925,51,168,316,404,899,903,941,376,103,436,917,722,19,355,698,407,298),
					new Ticket(316,114,89,623,672,167,95,935,685,686,193,654,303,713,234,783,747,626,868,392),
					new Ticket(195,72,901,486,241,159,60,58,442,211,837,217,352,279,516,156,833,661,442,265),
					new Ticket(872,267,101,83,349,513,486,533,241,113,236,445,885,672,833,675,854,931,574,165),
					new Ticket(479,448,730,617,289,185,585,308,784,91,90,53,661,632,307,722,116,539,794,412),
					new Ticket(105,483,64,795,482,486,737,853,483,723,679,726,570,744,376,307,382,854,348,608),
					new Ticket(134,443,920,418,553,753,217,624,311,75,555,382,597,297,309,855,153,89,778,583),
					new Ticket(579,653,681,819,110,830,50,303,604,783,249,339,835,929,229,108,19,926,516,949),
					new Ticket(74,496,584,170,115,297,93,497,921,916,571,846,782,597,437,744,458,347,110,607),
					new Ticket(903,488,448,355,377,852,872,773,511,731,429,81,393,228,518,192,625,61,490,239),
					new Ticket(118,168,576,220,670,495,921,716,438,112,671,782,751,339,928,65,293,592,266,270),
					new Ticket(237,841,409,525,584,381,422,238,2,731,404,803,220,877,295,821,918,919,946,727),
					new Ticket(387,528,847,619,498,782,745,68,444,597,545,315,580,794,700,660,343,936,718,57),
					new Ticket(877,799,432,495,87,318,489,379,461,598,418,658,595,403,82,894,298,237,837,77),
					new Ticket(587,310,599,886,784,417,232,450,947,405,478,576,52,936,300,862,783,189,377,918),
					new Ticket(113,390,605,584,870,542,308,791,300,603,883,798,586,841,685,777,782,587,895,151),
					new Ticket(304,724,215,414,873,841,617,619,861,400,308,479,455,104,620,545,159,783,654,235),
					new Ticket(802,311,157,465,159,882,393,377,745,436,724,427,854,102,174,422,169,700,380,447),
					new Ticket(91,862,280,399,464,396,407,134,117,678,903,307,341,747,875,243,550,728,926,555),
					new Ticket(496,861,940,577,441,80,285,241,553,390,937,271,467,410,408,870,977,752,354,579),
					new Ticket(553,894,294,731,326,414,406,83,828,654,82,170,667,112,853,94,188,434,527,295),
					new Ticket(803,557,169,672,82,655,464,350,157,399,691,160,745,975,902,428,877,891,213,108),
					new Ticket(340,383,887,112,719,519,683,853,190,899,161,793,850,403,437,52,890,802,593,609),
					new Ticket(229,722,783,899,785,247,434,587,936,248,609,265,84,853,831,544,861,780,781,469),
					new Ticket(301,312,675,897,263,397,392,476,928,67,467,653,697,861,288,231,623,246,669,839),
					new Ticket(273,339,78,861,339,212,703,402,285,419,11,931,880,415,246,622,72,934,227,525),
					new Ticket(861,696,672,414,265,154,246,375,585,478,110,853,406,277,311,112,612,394,274,415),
					new Ticket(896,868,338,782,467,314,60,925,224,585,267,297,984,169,272,683,390,347,784,292),
					new Ticket(917,516,424,213,802,385,310,871,154,325,872,190,898,476,61,513,464,919,211,97),
					new Ticket(468,682,520,538,695,419,404,350,598,121,495,300,858,421,542,822,778,76,283,275),
					new Ticket(752,71,896,151,851,842,606,608,667,257,97,744,288,317,233,778,618,276,487,492),
					new Ticket(345,191,426,858,306,398,691,230,755,472,614,381,937,850,607,729,477,889,625,215),
					new Ticket(288,622,864,902,719,732,219,830,858,356,288,841,830,248,184,667,198,550,663,498),
					new Ticket(246,428,5,690,693,745,238,223,153,220,193,852,744,192,596,575,832,944,585,401),
					new Ticket(296,542,87,338,732,437,800,874,309,843,58,732,665,103,80,430,850,532,673,286),
					new Ticket(374,239,653,794,918,829,725,927,534,151,745,354,575,676,681,941,749,860,70,467),
					new Ticket(60,185,832,191,373,841,845,292,797,811,82,883,270,931,552,218,575,690,239,190),
					new Ticket(778,74,518,267,374,227,110,73,855,679,916,796,339,661,551,989,683,829,151,67),
					new Ticket(437,824,70,295,752,600,522,113,624,784,188,921,488,882,889,389,266,732,337,928),
					new Ticket(803,345,300,869,232,868,703,247,796,217,834,17,599,919,856,277,160,468,287,55),
					new Ticket(302,856,395,248,848,223,81,54,679,695,580,821,558,280,827,691,746,385,896,476),
					new Ticket(948,556,343,921,383,925,97,174,937,311,166,585,394,600,686,491,698,389,274,921),
					new Ticket(240,749,50,291,430,603,899,545,156,403,456,386,658,703,382,73,337,315,929,681),
					new Ticket(922,101,625,668,376,664,223,657,298,175,889,544,752,802,621,926,847,513,88,518),
					new Ticket(905,676,474,340,847,430,108,303,725,62,578,419,747,70,660,405,218,891,181,487),
					new Ticket(406,420,674,67,931,297,314,942,586,396,245,623,551,652,250,590,77,100,657,558),
					new Ticket(542,490,61,750,223,888,224,941,875,581,222,527,217,223,864,574,287,182,528,475),
					new Ticket(830,305,239,924,316,337,289,289,431,344,898,167,594,926,785,795,933,558,290,932),
					new Ticket(934,873,488,729,756,240,933,130,51,489,512,621,79,671,408,229,102,872,68,523),
					new Ticket(377,70,584,213,131,275,489,433,689,747,50,753,88,217,439,470,597,835,390,904),
					new Ticket(353,580,851,791,888,841,937,234,835,74,598,555,582,422,337,352,64,338,433,870),
					new Ticket(750,701,156,249,725,857,354,703,126,851,660,243,623,492,695,76,490,844,335,886),
					new Ticket(859,216,380,425,490,479,936,445,326,938,574,418,848,489,87,386,800,97,303,673),
					new Ticket(303,902,73,898,619,403,540,355,55,236,161,708,940,920,83,79,246,356,922,887),
					new Ticket(318,79,157,888,586,213,526,659,318,945,570,496,947,799,419,779,128,860,154,440),
					new Ticket(168,554,934,573,594,528,512,522,398,294,738,89,474,104,833,583,849,338,306,666),
					new Ticket(521,522,591,924,558,657,554,488,93,590,378,152,194,190,193,727,403,509,291,395),
					new Ticket(943,50,379,392,427,422,916,894,273,473,68,284,341,514,847,301,494,295,384,134),
					new Ticket(876,797,487,698,194,571,714,59,665,655,78,79,108,281,293,891,849,898,387,551),
					new Ticket(182,923,483,524,426,94,237,602,690,493,884,434,411,551,750,528,625,396,249,526),
					new Ticket(343,495,784,355,794,494,240,678,191,719,848,753,243,148,115,211,842,750,390,622),
					new Ticket(153,552,548,284,164,694,383,283,821,580,376,312,887,861,247,881,259,279,345,868),
					new Ticket(443,152,228,853,438,343,654,85,850,290,277,58,524,114,5,300,936,668,602,112),
					new Ticket(884,976,606,239,900,872,863,397,306,866,866,864,938,586,905,594,497,899,394,828),
					new Ticket(889,241,241,512,877,231,879,229,474,424,275,888,539,590,831,982,725,407,248,154),
					new Ticket(193,727,107,544,476,402,856,673,132,388,848,748,751,335,407,424,837,93,916,526),
					new Ticket(655,395,931,744,405,780,75,286,429,679,580,876,543,525,932,670,699,920,138,936),
					new Ticket(745,20,297,266,223,478,353,843,893,52,655,473,74,655,248,689,745,222,702,160),
					new Ticket(664,778,443,923,750,547,431,892,864,211,289,471,442,626,848,73,546,126,187,219),
					new Ticket(692,685,289,479,378,238,884,443,317,151,787,877,109,482,850,753,945,621,352,689),
					new Ticket(64,291,294,577,241,112,557,744,84,690,315,113,854,109,625,996,269,486,662,576),
					new Ticket(920,287,623,161,315,621,348,492,664,281,946,381,606,752,309,992,186,184,68,895),
					new Ticket(879,577,107,432,654,870,439,467,419,236,447,549,219,353,485,936,213,412,823,288),
					new Ticket(790,665,747,494,525,863,421,425,871,391,422,601,215,242,609,784,214,727,869,162),
					new Ticket(750,551,756,373,100,356,222,373,857,50,236,52,116,858,930,948,668,730,936,827),
					new Ticket(485,899,839,584,622,867,242,373,654,209,169,87,80,349,538,271,820,222,483,620),
					new Ticket(221,164,293,687,656,595,724,419,2,893,385,940,656,112,113,835,407,524,703,777),
					new Ticket(164,598,491,834,416,271,216,688,945,92,652,266,872,50,733,291,796,310,900,873),
					new Ticket(230,918,93,892,376,412,470,679,823,526,353,426,474,609,674,114,876,540,278,221),
					new Ticket(438,310,918,228,141,874,228,89,51,58,861,553,425,603,154,862,849,657,553,889),
					new Ticket(686,557,153,875,423,549,620,484,391,156,921,68,223,309,219,472,718,625,591,164),
					new Ticket(656,487,240,520,276,439,239,888,834,95,132,65,413,557,90,303,299,890,247,590),
					new Ticket(436,228,868,491,696,403,118,939,819,698,64,819,237,780,341,849,306,52,791,929),
					new Ticket(692,440,848,418,886,298,88,154,790,595,755,662,307,587,866,837,50,722,398,384),
					new Ticket(124,894,570,311,428,938,52,661,348,703,599,624,441,168,588,606,292,105,819,582),
					new Ticket(682,374,296,591,117,388,221,386,307,933,830,548,584,480,918,689,671,912,935,81),
					new Ticket(76,621,821,578,90,348,68,279,66,613,288,268,518,238,881,583,830,514,382,410),
					new Ticket(99,385,317,864,265,831,801,209,374,413,420,117,939,802,590,651,433,380,931,858),
					new Ticket(495,849,430,229,349,63,214,682,477,238,868,516,435,341,188,749,595,785,888,893),
					new Ticket(56,215,279,246,832,71,802,870,663,102,872,879,137,752,393,675,784,56,55,928),
					new Ticket(396,691,335,448,492,402,243,780,665,753,782,556,383,799,332,409,162,389,936,719),
					new Ticket(827,838,579,286,265,164,875,847,699,596,694,799,948,542,117,356,83,678,798,699),
					new Ticket(375,897,425,188,653,838,177,837,317,878,522,801,308,230,830,626,926,842,292,592),
					new Ticket(90,932,56,266,335,830,675,64,193,828,930,902,946,520,483,232,738,521,842,577),
					new Ticket(802,466,400,207,929,314,236,246,356,70,350,309,281,153,616,839,388,389,307,881),
					new Ticket(434,57,466,435,292,221,398,558,729,553,169,215,880,781,988,860,835,467,688,701),
					new Ticket(525,475,270,552,225,169,106,190,236,700,266,413,414,651,167,283,527,615,282,233),
					new Ticket(843,161,441,605,856,336,269,938,617,925,165,394,601,446,628,341,603,603,387,429),
					new Ticket(392,573,550,931,56,938,167,742,342,589,495,940,286,934,794,467,550,939,853,77),
					new Ticket(54,476,748,674,355,665,576,440,480,85,88,317,898,679,459,836,843,436,388,436),
					new Ticket(936,588,98,238,899,655,474,878,778,290,747,976,378,443,845,948,583,245,519,623),
					new Ticket(429,277,97,694,678,701,600,902,667,312,478,581,921,522,520,872,299,773,480,164),
					new Ticket(543,684,242,572,825,400,59,226,77,902,78,604,720,890,684,71,242,99,864,594),
					new Ticket(494,163,588,211,794,75,458,161,292,679,488,575,226,401,75,947,729,938,690,885),
					new Ticket(831,578,112,240,979,229,93,928,841,281,437,285,620,581,346,240,235,874,242,387),
					new Ticket(836,95,374,822,843,211,306,82,945,690,24,114,543,470,58,218,117,658,617,724),
					new Ticket(402,881,248,485,703,574,898,655,233,597,487,412,495,458,872,346,273,727,86,190),
					new Ticket(849,439,853,939,859,616,669,243,875,671,650,76,218,747,299,699,883,930,545,884),
					new Ticket(104,513,76,170,591,587,403,896,693,487,703,836,710,92,231,228,830,226,288,53),
					new Ticket(740,389,110,755,491,555,574,421,294,478,445,428,519,796,622,74,97,302,50,783),
					new Ticket(381,381,626,868,621,468,850,345,101,719,266,851,490,519,727,222,106,263,943,442),
					new Ticket(289,313,317,783,448,698,74,117,83,834,835,432,595,74,533,475,356,872,189,106),
					new Ticket(234,488,117,901,831,995,471,888,268,725,799,375,750,222,488,516,522,103,662,157),
					new Ticket(537,377,58,550,599,237,356,170,848,157,492,794,270,852,355,318,382,677,112,71),
					new Ticket(473,277,749,593,311,888,278,670,376,463,732,779,99,486,552,356,113,597,376,871),
					new Ticket(583,377,216,477,71,163,664,888,339,798,436,996,378,443,747,830,315,272,592,574),
					new Ticket(555,618,188,831,85,465,479,380,94,737,802,337,882,431,302,217,543,187,654,388),
					new Ticket(527,93,943,549,891,70,792,589,899,409,393,234,703,350,378,517,783,347,546,783),
					new Ticket(292,17,90,653,417,314,158,231,839,155,448,849,936,497,877,111,488,294,478,861),
					new Ticket(116,664,74,652,619,395,798,445,831,718,80,245,94,601,432,109,489,873,229,795),
					new Ticket(214,109,436,871,558,516,946,239,223,936,601,667,392,208,441,420,308,598,602,798),
					new Ticket(751,599,623,784,462,701,70,443,286,274,348,296,221,546,248,469,589,415,65,690),
					new Ticket(699,341,904,475,682,860,614,68,581,245,422,283,847,117,428,693,340,476,669,522),
					new Ticket(880,237,66,52,879,346,597,603,679,840,530,924,378,340,667,586,384,57,90,526),
					new Ticket(609,305,214,869,350,679,872,617,207,587,415,113,865,106,225,400,285,214,287,730),
					new Ticket(899,309,274,74,114,399,76,598,308,521,200,541,441,876,57,277,885,887,894,548),
					new Ticket(410,186,317,307,402,436,483,920,470,753,492,66,553,844,283,604,755,197,554,315),
					new Ticket(697,662,727,593,644,890,315,448,396,214,676,73,575,314,285,747,862,618,835,423),
					new Ticket(872,893,379,848,216,65,940,288,51,757,231,653,542,570,352,479,831,838,619,440),
					new Ticket(576,695,665,304,745,793,470,306,609,555,842,431,860,662,284,897,659,784,598,512),
					new Ticket(699,66,190,276,741,538,833,548,430,921,686,387,161,437,96,281,424,413,307,513),
					new Ticket(300,295,224,432,663,617,285,519,94,573,721,754,476,429,688,86,647,75,192,593),
					new Ticket(617,53,289,551,755,90,137,304,493,67,446,408,95,618,796,700,847,720,106,118),
					new Ticket(936,545,337,172,384,602,690,344,59,347,102,581,551,115,404,480,821,586,59,243),
					new Ticket(709,269,57,265,412,601,273,64,74,859,782,725,945,598,224,671,942,289,756,781),
					new Ticket(476,5,219,93,306,693,94,374,929,95,350,680,302,226,939,274,681,528,75,850),
					new Ticket(585,427,411,440,335,166,887,754,174,920,574,72,304,553,865,921,596,523,587,438),
					new Ticket(354,379,277,6,167,777,802,414,942,695,723,158,221,393,191,52,432,314,852,298),
					new Ticket(160,266,997,690,590,432,890,302,857,526,284,237,394,464,189,169,289,687,247,52),
					new Ticket(673,265,387,920,574,859,659,158,882,268,886,316,310,114,312,347,539,818,590,419),
					new Ticket(751,163,435,521,314,394,70,540,403,238,160,827,819,422,659,920,426,605,479,745),
					new Ticket(476,719,519,151,997,422,301,684,944,383,275,93,656,392,218,729,513,626,493,293),
					new Ticket(597,698,549,620,512,901,991,778,272,512,415,416,466,216,667,527,901,605,662,413),
					new Ticket(79,286,729,312,829,93,431,69,52,269,218,513,52,788,350,231,288,777,435,542),
					new Ticket(394,589,93,90,162,375,295,799,84,939,160,461,239,213,856,798,547,374,337,723),
					new Ticket(54,720,751,852,431,380,834,902,947,358,219,746,558,749,598,477,882,270,92,732),
					new Ticket(669,625,938,604,397,935,889,557,240,313,272,928,861,577,671,447,519,331,864,546),
					new Ticket(749,579,600,398,692,447,443,56,623,998,165,871,233,213,284,527,683,583,242,588),
					new Ticket(439,469,853,24,386,65,193,518,83,409,353,213,916,486,224,573,76,385,439,672),
					new Ticket(66,383,943,871,373,992,861,605,514,675,693,593,864,351,853,557,401,88,88,862),
					new Ticket(597,439,484,431,378,271,796,344,713,899,597,870,59,277,784,512,588,66,897,868),
					new Ticket(840,93,744,484,183,88,687,922,607,842,95,573,605,246,799,241,583,600,428,352),
					new Ticket(65,835,163,404,940,826,822,514,858,851,778,578,266,238,435,281,694,286,283,388),
					new Ticket(687,869,54,272,536,266,572,853,478,726,403,722,919,943,840,756,828,248,466,164),
					new Ticket(346,624,314,495,277,923,198,102,834,891,309,475,118,342,489,687,245,548,409,440),
					new Ticket(846,621,557,158,674,574,395,298,582,679,211,869,79,376,894,2,343,302,51,335),
					new Ticket(558,439,61,466,350,106,111,668,298,291,282,905,835,547,101,20,604,899,55,655),
					new Ticket(545,158,686,541,587,829,477,780,543,858,288,684,307,439,159,829,517,654,424,790),
					new Ticket(293,224,902,822,696,391,520,346,620,525,86,293,468,557,279,307,978,939,720,275),
					new Ticket(291,279,439,678,338,932,345,545,747,676,72,393,498,936,302,889,16,515,689,900),
					new Ticket(74,583,858,572,751,590,526,864,556,67,56,294,213,394,195,235,719,383,885,275),
					new Ticket(549,877,439,272,625,599,748,214,381,731,744,515,823,226,667,939,108,395,658,476),
					new Ticket(58,223,60,217,349,164,273,903,836,686,823,587,518,597,314,291,73,466,223,675),
					new Ticket(170,444,651,429,476,159,421,575,614,920,99,447,278,571,84,83,312,727,904,338),
					new Ticket(98,106,349,683,749,282,830,661,648,538,282,307,528,70,291,547,848,600,443,593),
					new Ticket(781,350,616,698,589,581,185,724,104,147,68,69,522,490,606,695,653,799,897,318),
					new Ticket(151,234,940,295,751,160,388,246,517,941,485,898,654,602,803,494,619,2,102,353),
					new Ticket(631,429,731,95,781,652,383,862,54,240,691,157,616,292,272,596,386,234,702,390),
					new Ticket(528,51,994,96,189,472,291,831,156,169,56,623,471,663,108,212,354,385,725,652),
					new Ticket(249,751,657,270,187,604,839,331,216,779,802,355,296,822,899,237,447,187,168,926),
					new Ticket(669,749,235,489,540,161,426,820,166,779,9,427,225,91,863,527,657,297,577,59),
					new Ticket(51,164,116,586,547,102,890,410,440,192,921,592,484,825,423,375,191,347,284,399),
					new Ticket(344,831,469,295,77,864,474,438,802,232,514,947,468,580,473,142,444,547,597,158),
					new Ticket(281,598,527,933,338,237,161,237,934,933,704,517,698,98,663,495,605,677,57,856),
					new Ticket(887,95,395,701,221,728,574,400,730,427,167,94,56,860,784,444,847,662,780,980),
					new Ticket(582,888,545,385,422,487,382,900,223,829,873,435,217,653,314,932,488,976,584,930),
					new Ticket(433,940,609,308,671,377,828,114,604,544,107,102,215,290,428,168,145,352,230,946),
					new Ticket(309,382,394,693,613,549,666,918,473,832,220,169,848,874,292,343,161,782,492,160),
					new Ticket(281,847,880,890,196,297,212,664,698,653,375,905,546,354,106,606,828,249,300,940),
					new Ticket(780,382,592,406,875,798,434,685,300,633,69,314,101,557,311,214,661,381,881,783),
					new Ticket(524,578,295,141,389,162,299,79,596,873,443,576,841,747,539,80,731,345,603,347),
					new Ticket(212,244,336,831,799,434,570,933,949,662,114,891,903,656,408,835,682,451,853,229),
					new Ticket(267,156,408,933,373,401,589,578,739,653,306,723,430,220,593,580,748,194,403,271),
					new Ticket(487,851,152,636,434,528,851,63,575,606,284,695,658,783,337,223,90,489,189,948),
					new Ticket(100,288,70,516,904,187,648,153,271,409,725,314,686,889,285,701,443,306,439,544));
	static Ticket test1myTicket = new Ticket(7,1,14);
	static List<Field> test1Fields = Arrays.asList(
			new Field("class", Range.range(1, BoundType.CLOSED, 3, BoundType.CLOSED),
					Range.range(5, BoundType.CLOSED, 7, BoundType.CLOSED), test1myTicket.size()),
			new Field("row", Range.range(6, BoundType.CLOSED, 11, BoundType.CLOSED),
					Range.range(33, BoundType.CLOSED, 44, BoundType.CLOSED), test1myTicket.size()),
			new Field("seat", Range.range(13, BoundType.CLOSED, 40, BoundType.CLOSED),
					Range.range(45, BoundType.CLOSED, 50, BoundType.CLOSED), test1myTicket.size()));
	static List<Ticket> test1NearbyTickets = Arrays.asList(new Ticket(7, 3, 47), new Ticket(40, 4, 50),
			new Ticket(55, 2, 20), new Ticket(38, 6, 12));
	
	@Test
	public void test() {
		// Part one
		Range<Integer> range = Range.range(1, BoundType.CLOSED, 5, BoundType.CLOSED);
		assertTrue(range.contains(3));
		assertTrue(range.contains(1));
		assertTrue(range.contains(5));
		assertFalse(range.contains(6));
		
		assertEquals(28882, getTicketScanningErrorRate());
		
		// Part two
		Input input = new Input(fields, myTicket, nearbyTickets);

		assertTrue(getTicketsToDiscard(test1NearbyTickets, test1Fields).contains(new Ticket(40,4,50)));
		assertEquals(3, getTicketsToDiscard(test1NearbyTickets, test1Fields).size());
		assertFalse(getTicketsToDiscard(test1NearbyTickets, test1Fields).contains(new Ticket(7,3,47)));
		List<Ticket> copy = new ArrayList<Ticket>(test1NearbyTickets);
		copy.removeAll(getTicketsToDiscard(test1NearbyTickets, test1Fields));
		assertEquals(1, copy.size());
		
		List<Ticket> validNearbyTickets = new ArrayList<Ticket>(nearbyTickets);
		validNearbyTickets.removeAll(getTicketsToDiscard(nearbyTickets, fields));
		for (Field field : fields) {
			for (Ticket ticket : validNearbyTickets) {
				for (int i = 0; i < ticket.size(); i++) {
					Integer value = ticket.get(i);
					if (field.availablePositions.contains(i) && !field.contains(value)) {
						field.availablePositions.remove(Integer.valueOf(i));
					}
					
				}
			}
		}
		// Normalizzo andando per esclusione
		while(!foundAllPosition(fields)) {
			normalize(fields);			
		}
		System.out.println(input.getMultiply(START_WITH, 3, 11, 12, 13, 18));
		System.out.println(input.getMultiply(START_WITH, 3, 11, 12, 13, 20,18));
		System.out.println(input.getMultiply(START_WITH, 3, 11, 12, 13, 1,18));

		
		System.out.println(input.getMultiply(START_WITH));
	}
	
	private boolean foundAllPosition(List<Field> fields) {
		// TODO
		Iterable<Field> filter = Iterables.filter(fields, new Predicate<Field>() {
			public boolean apply(Field arg0) {
				return arg0.name.startsWith(START_WITH);
			}
		});
		
		for (Field field : filter) {
			if (field.availablePositions.size() > 1) {
				return false;
			}
		}
		return true;
	}

	private void normalize(List<Field> fields) {
		Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			for (Integer pos : field.availablePositions) {
				if (map.containsKey(pos)) {
					map.get(pos).add(i);
				} else {
					List<Integer> list = new ArrayList<Integer>();
					list.add(i);
					map.put(pos, list);
				}
				
			}
			for (Entry<Integer, List<Integer>> entry : map.entrySet()) {
				if (entry.getValue().size() == 1) {
					List<Integer> list = new ArrayList<Integer>();
					list.add(entry.getKey());
					fields.get(entry.getValue().get(0)).availablePositions = list;
				}
			}
			if (field.availablePositions.size() == 1) {
				continue;
			}
			List<Field> subList = new ArrayList<Field>();
			if (i == 0) {
				subList.addAll(fields.subList(1, fields.size()));
			} else if (i == fields.size()-1) {
				subList.addAll(fields.subList(0, i));
			} else {
				subList.addAll(fields.subList(0, i));
				subList.addAll(fields.subList(i+1, fields.size()));				
			}
			for (Field field2 : subList) {
				if (field2.availablePositions.size() > 1) {
					continue;
				}
				// Do per scontato che altrimenti ci stia solo un elemento
				field.availablePositions.remove(field2.availablePositions.get(0));
			}
		}
	}

	private long getTicketScanningErrorRate() {
		long result = 0;
		for (Ticket ticket : nearbyTickets) {			
			for (Integer value : ticket) {
				int i = 0;
				while (i < fields.size()) {
					Field field = fields.get(i);
					if (field.contains(value)) {
						break;
					}
					i++;
				}
				if (i == fields.size()) {
					result += value;
				}
			}
		}
		return result;
	}

	private List<Ticket> getTicketsToDiscard(List<Ticket> nearbyTickets, List<Field> fields) {
		List<Ticket> result = new ArrayList<Ticket>();
		for (Ticket ticket : nearbyTickets) {			
			for (Integer value : ticket) {
				int i = 0;
				while (i < fields.size()) {
					Field field = fields.get(i);
					if (field.contains(value)) {
						break;
					}
					i++;
				}
				if (i == fields.size()) {
					result.add(ticket);
				}
			}
		}
		return result;
	}

	static class Field {
		String name;
		Range<Integer> range1;
		Range<Integer> range2;
		List<Integer> availablePositions;

		Field(String name, Range<Integer> range1, Range<Integer> range2, int ticketLength) {
			this.name = name;
			this.range1 = range1;
			this.range2 = range2;
			availablePositions = new ArrayList<Integer>();
			for (int i = 1; i <= ticketLength; i++) {
				availablePositions.add(i);
			}
		}
		

		public Field(String string, Object range, Object range2, int size) {
			// TODO Auto-generated constructor stub
		}


		@Override
		public String toString() {
			return "[" + name + ", " + availablePositions + "]";
		}


		boolean contains(int value) {
			return range1.contains(value) || range2.contains(value);
		}
	}
	
	static class Input {
		List<Field> fields;
		Ticket myTicket;
		List<Ticket> nearbyTickets;
		Input(List<Field> fields, Ticket myTicket, List<Ticket> nearbyTickets) {
			this.fields = fields;
			this.myTicket = myTicket;
			this.nearbyTickets = nearbyTickets;
		}
		
		public Long getMultiply(String startWith, int... i) {
			long result = 1;
			for (int j : i) {
				result *= myTicket.get(j-1);
			}
			return result;
		}

		private Long getMultiply(final String fieldStartWith) {
			long result = 1;
			Iterable<Field> filter = Iterables.filter(fields, new Predicate<Field>() {
				public boolean apply(Field arg0) {
					return arg0.name.startsWith(fieldStartWith);
				}
			});
			for (Field field : filter) {
//				for (Ticket ticket : nearbyTickets) {
					if (field.availablePositions.size() > 1) {
						throw new IllegalArgumentException();
					}
					result *= myTicket.get(field.availablePositions.get(0)); 
//				}
			}
			return result;
		}
		
	}
	
	static class Ticket extends ArrayList<Integer> {

		public Ticket(Integer... i) {
			super(Arrays.asList(i));
		}

		private static final long serialVersionUID = 1L;
		
	}

}
