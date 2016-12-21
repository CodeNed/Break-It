package com.noah.breakit.stagepatterns;

import java.util.ArrayList;
import java.util.List;

public class StagePattern {

	public static final char[] VOID_STAGE = {

			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };

	public static final char[] STAGE_1 = {

			'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '#', '#', '#', '#', '#',
			'#', '#', '#', '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, };

	public static final char[] STAGE_2 = {

			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '#', '#', '#', '#', '#', '#', '#', '#', '#',
			'#', '#', '#', '#', '#', '#', '#', '#', '#', 0, 0, '#', '#', '#', '#', '#', '#', 0, 0, 0, 0, '#', '#', '#',
			'#', 0, 0, 0, 0, 0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0,

	};

	public static final char[] STAGE_3 = {

			'#', '#', 0, 0, 0, 0, 0, 0, '#', '#', 0, '#', '#', 0, 0, 0, 0, '#', '#', 0, 0, 0, '#', '#', 0, 0, '#', '#',
			0, 0, 0, 0, 0, '#', '#', '#', '#', 0, 0, 0, 0, 0, 0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, '#', '#', '#', '#',
			0, 0, 0, 0, 0, '#', '#', 0, 0, '#', '#', 0, 0, 0, '#', '#', 0, 0, 0, 0, '#', '#', 0, '#', '#', 0, 0, 0, 0,
			0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0 };

	public static final char[] STAGE_4 = {

			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '#', 0, 0, 0, 0, '#', 0, 0, 0, '#', '#', '#', 0, 0, '#', '#', '#', 0, 0,
			'#', '#', '#', 0, 0, '#', '#', '#', 0, 0, 0, '#', 0, 0, 0, 0, '#', 0, 0, 0, 0, 0, 0, '#', '#', 0, 0, 0, 0,
			0, 0, 0, '#', '#', '#', '#', 0, 0, 0, 0, 0, '#', '#', 0, 0, '#', '#', 0, 0, 0, '#', '#', 0, 0, 0, 0, '#',
			'#', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0 };

	public static final char[] STAGE_5 = {

			0, 0, 0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, '#', '#', '#', 0, 0, 0, 0, 0, 0, 0, '#', '#', 0, 0, 0, 0, 0,
			0, 0, '#', '#', '#', '#', 0, 0, 0, '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', 0, '#', '#', '#', '#',
			'#', '#', '#', '#', 0, 0, 0, '#', '#', '#', '#', '#', '#', 0, 0, 0, 0, 0, '#', 0, 0, '#', 0, 0, 0, 0, 0, 0,
			'#', 0, 0, '#', 0, 0, 0, 0, 0, '#', 0, 0, 0, 0, '#', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	public static final char[] STAGE_6 = {

			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '#', '#', 0, 0, 0, '#', '#', 0, 0, 0, '#',
			'#', 0, 0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '#', 0, 0,
			0, '#', 0, 0, 0, 0, 0, '#', 0, 0, 0, '#', 0, 0, 0, 0, 0, '#', 0, 0, 0, '#', 0, 0, 0, 0, 0, '#', 0, 0, 0,
			'#', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '#', 0, 0, 0,
			0, 0, 0, 0, 0, 0, '#', 0, 0, 0, 0, '#', '#', '#', '#', '#', 0, 0, 0, 0, 0, '#', '#', '#', '#', '#', 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0 };

	public static final char[] STAGE_7 = {

			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, '#', 0, 0, 0, 0, 0, 0, '#', 0, 0, '#', 0, 0, 0, 0, 0, 0, '#', 0, 0, 0, '#',
			0, 0, 0, 0, '#', 0, 0, 0, 0, '#', 0, 0, 0, 0, '#', 0, 0, 0, 0, 0, '#', 0, 0, '#', 0, 0, 0, 0, 0, 0, '#', 0,
			0, '#', 0, 0, 0, 0, 0, 0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, '#', '#',
			0, 0, 0, 0, 0, 0, 0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, '#', '#', 0, 0,
			0, 0, 0, 0, 0, '#', '#', '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0 };

	public static final char[] STAGE_8 = {

			0, 0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, 0, '#', 0, 0, 0, 0, 0, 0, 0, 0,
			0, '#', 0, 0, 0, 0, 0, 0, 0, 0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, '#', '#', '#', '#', 0, 0, 0, 0, 0, 0, '#',
			0, 0, '#', 0, 0, 0, 0, 0, 0, '#', 0, 0, '#', 0, 0, 0, 0, 0, '#', '#', '#', '#', '#', '#', 0, 0, 0, '#', '#',
			'#', '#', '#', '#', '#', '#', 0, '#', 0, 0, 0, 0, 0, 0, '#', '#', '#', '#', 0, 0, 0, 0, 0, 0, '#', '#', '#',
			'#', 0, 0, 0, 0, 0, 0, 0, 0, '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, '#', 0, '#', '#', '#', '#', '#', '#', '#',
			'#', 0, 0, 0, '#', '#', '#', '#', '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };

	public static final char[] STAGE_9 = {

			'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
			'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
			'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
			'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
			'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };

	public static final char STAGE_10[] = { '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#',
			'#', '#', '0', '0', '0', '0', '#', '#', '#', '#', '#', '#', '#', '0', '0', '0', '#', '#', '#', '#', '#',
			'#', '#', '0', '0', '0', '#', '#', '0', '0', '0', '#', '#', '#', '0', '0', '#', '#', '0', '0', '0', '#',
			'#', '#', '0', '0', '0', '#', '#', '0', '#', '0', '#', '#', '0', '0', '0', '#', '#', '0', '#', '0', '#',
			'#', '0', '0', '0', '#', '0', '0', '0', '0', '0', '#', '0', '0', '0', '#', '0', '0', '0', '0', '0', '#',
			'0', '0', '0', '#', '0', '0', '0', '0', '#', '0', '#', '0', '0', '#', '0', '0', '0', '0', '#', '0', '#',
			'0', '0', '0', '#', '#', '#', '#', '0', '#', '#', '0', '0', '0', '#', '#', '#', '#', '0', '#', '#', '0',
			'0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_11[] = { '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#',
			'#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#',
			'0', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#',
			'0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '#', '0', '0', '0', '0', '#', '#', '#', '#', '#', '#',
			'0', '0', '0', '#', '#', '#', '#', '#', '#', '#', '#', '0', '0', '#', '#', '#', '#', '#', '#', '#', '#',
			'0', '0', '#', '#', '#', '#', '#', '#', '#', '#', '0', '0', '#', '#', '#', '#', '#', '#', '#', '#', '0',
			'0', '0', '#', '#', '#', '#', '#', '#', '0', '0', '0', '0', '#', '#', '#', '#', '#', '#', '0', '0', '0',
			'0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0',
			'0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_12[] = { '0', '0', '#', '#', '0', '0', '#', '#', '0', '0', '0', '0', '#', '#', '0',
			'0', '#', '#', '0', '0', '0', '#', '#', '#', '#', '#', '#', '#', '#', '0', '0', '#', '#', '#', '#', '#',
			'#', '#', '#', '0', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
			'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
			'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
			'#', '0', '#', '#', '#', '#', '#', '#', '#', '#', '0', '0', '#', '#', '#', '#', '#', '#', '#', '#', '0',
			'0', '0', '#', '#', '#', '#', '#', '#', '0', '0', '0', '0', '#', '#', '#', '#', '#', '#', '0', '0', '0',
			'0', '0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#', '0', '0', '0', '0', '0',
			'0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_13[] = { '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#',
			'#', '0', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0', '#', '#', '#',
			'#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '#', '0', '0', '0', '0', '#', '#', '#', '#', '#',
			'#', '0', '0', '0', '#', '#', '#', '#', '#', '#', '#', '#', '0', '0', '#', '#', '#', '#', '#', '#', '#',
			'#', '0', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
			'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
			'0', '#', '#', '#', '#', '#', '#', '#', '#', '0', '0', '#', '#', '#', '#', '#', '#', '#', '#', '0', '0',
			'0', '#', '#', '#', '#', '#', '#', '0', '0', '0', '0', '#', '#', '#', '#', '#', '#', '0', '0', '0', '0',
			'0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0',
			'0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0' };

	public static final char STAGE_14[] = { '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#',
			'#', '0', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0', '#', '#', '#',
			'#', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#',
			'0', '0', '0', '0', '#', '#', '#', '#', '#', '#', '#', '#', '0', '0', '#', '#', '#', '#', '#', '#', '#',
			'#', '0', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
			'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
			'0', '#', '#', '#', '#', '#', '#', '#', '#', '0', '0', '#', '#', '#', '#', '#', '#', '#', '#', '0', '0',
			'0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0',
			'0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_15[] = { '#', '#', '0', '0', '0', '0', '0', '0', '#', '#', '#', '0', '0', '0', '0',
			'0', '0', '0', '0', '#', '0', '0', '0', '#', '0', '0', '#', '0', '0', '0', '0', '0', '0', '#', '0', '0',
			'#', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '#', '#', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '#', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'#', '0', '0', '#', '0', '0', '#', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '#', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_16[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0', '#', '#', '#',
			'#', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '#', '0', '0', '0', '0', '#', '0', '0', '0', '0',
			'#', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '#', '0', '0', '#', '#', '0', '0', '0', '0', '0',
			'#', '0', '0', '#', '#', '0', '0', '0', '0', '0', '#', '0', '0', '#', '#', '0', '0', '0', '0', '0', '#',
			'0', '0', '#', '0', '#', '0', '0', '0', '#', '#', '0', '0', '#', '0', '#', '0', '0', '0', '#', '#', '0',
			'0', '0', '#', '#', '#', '#', '#', '0', '#', '0', '0', '0', '#', '#', '#', '#', '#', '0', '#', '0', '0',
			'0', '#', '0', '0', '0', '0', '0', '#', '0', '0', '0', '#', '0', '0', '0', '0', '0', '#', '0', '0', '0',
			'0', '#', '0', '0', '0', '#', '0', '0', '0', '0', '0', '#', '0', '0', '0', '#', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_17[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '#', '0', '0', '0', '0', '#', '#', '#', '#',
			'#', '#', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '#', '0', '0', '#', '0', '0', '0', '0', '0',
			'0', '#', '0', '0', '0', '#', '0', '0', '0', '0', '#', '0', '0', '0', '0', '#', '0', '0', '0', '0', '#',
			'0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '#', '0', '0', '#', '0', '0', '0', '0', '0', '0', '#',
			'0', '0', '#', '0', '0', '0', '0', '0', '0', '#', '0', '0', '#', '0', '0', '0', '0', '0', '0', '#', '0',
			'0', '0', '#', '0', '0', '0', '0', '#', '0', '0', '0', '0', '#', '0', '0', '0', '0', '#', '0', '0', '0',
			'#', '0', '0', '0', '0', '0', '0', '#', '0', '0', '#', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_18[] = { '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0',
			'#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'#', '0', '0', '0', '0', '0', '0', '#', '0', '#', '#', '0', '0', '0', '0', '0', '0', '#', '0', '#', '#',
			'0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_19[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '#', '0', '#', '0', '0', '0', '0', '0', '0', '0', '#', '0', '#', '0', '0', '0', '0', '0',
			'0', '0', '#', '#', '0', '0', '#', '#', '0', '#', '0', '0', '#', '#', '0', '0', '#', '#', '0', '#', '0',
			'0', '#', '0', '#', '0', '0', '#', '0', '0', '#', '0', '#', '0', '#', '0', '0', '#', '0', '0', '#', '0',
			'#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_20[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '#', '0', '#', '0', '#', '0', '0', '0', '0', '0', '#', '0', '#', '0', '#', '0',
			'0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '#', '0', '#', '0', '#', '0', '0', '0', '0', '0', '#',
			'0', '#', '0', '0', '0', '#', '0', '0', '0', '#', '0', '0', '#', '0', '0', '#', '0', '0', '0', '#', '0',
			'0', '#', '#', '0', '#', '0', '#', '0', '#', '0', '#', '#', '#', '0', '#', '0', '#', '0', '#', '0', '#',
			'#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_21[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0',
			'0', '0', '0', '0', '0', '#', '0', '#', '#', '0', '0', '0', '0', '0', '0', '#', '0', '#', '#', '0', '0',
			'0', '0', '0', '0', '#', '0', '#', '#', '0', '0', '0', '0', '0', '0', '#', '0', '#', '#', '0', '0', '0',
			'0', '0', '0', '#', '0', '#', '0', '#', '0', '0', '0', '0', '0', '#', '0', '#', '0', '#', '0', '0', '0',
			'0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_22[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0',
			'0', '0', '0', '#', '0', '#', '#', '0', '0', '0', '0', '0', '0', '#', '0', '#', '#', '0', '0', '0', '0',
			'0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_23[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0',
			'0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0',
			'0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0',
			'0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0',
			'#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_24[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '#', '#', '#', '0', '0', '0', '0', '0', '0', '0', '#', '#', '#', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0',
			'0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_25[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0',
			'0', '0', '0', '#', '0', '#', '#', '0', '0', '0', '0', '0', '0', '#', '0', '#', '#', '0', '0', '0', '0',
			'0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_26[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_27[] = { '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0',
			'0', '0', '#', '0', '#', '#', '0', '0', '0', '0', '0', '0', '#', '0', '#', '#', '0', '0', '0', '0', '0',
			'#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_28[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0',
			'0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0',
			'0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_29[] = { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '#', '#', '#', '0', '0', '0', '0', '0', '0', '0', '#', '#', '#', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0',
			'0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	public static final char STAGE_30[] = { '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0', '0', '0', '0', '0', '0', '#', '#', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0', '0', '0', '0', '0', '0', '#', '0', '0', '0', '0',
			'0', '0', '#', '0', '#', '#', '0', '0', '0', '0', '0', '0', '#', '0', '#', '#', '0', '0', '0', '0', '0',
			'#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '#', '#', '#', '#', '#', '0', '0', '0', '0', '0', '0',
			'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0' };

	private static final List<char[]> STAGEPATTERNS = new ArrayList<char[]>() {

		private static final long serialVersionUID = 1L;

		{
			add(STAGE_1);
			add(STAGE_2);
			add(STAGE_3);
			add(STAGE_4);
			add(STAGE_5);
			add(STAGE_6);
			add(STAGE_7);
			add(STAGE_8);
			add(STAGE_9);
			add(STAGE_10);
			add(STAGE_11);
			add(STAGE_12);
			add(STAGE_13);
			add(STAGE_14);
			add(STAGE_15);
			add(STAGE_16);
			add(STAGE_17);
			add(STAGE_18);
			add(STAGE_19);
			add(STAGE_20);
			add(STAGE_21);
			add(STAGE_22);
			add(STAGE_23);
			add(STAGE_24);
			add(STAGE_25);
			add(STAGE_26);
			add(STAGE_27);
			add(STAGE_28);
			add(STAGE_29);
			add(STAGE_30);
			
		}
	};

	private StagePattern() {
	}

	public static char[] getStagePattern(int index) {
		return STAGEPATTERNS.get(index);
	}
}
