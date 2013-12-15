package com.digiarea.closure.model.bind;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import com.digiarea.closure.model.GssOutputFormat;
import com.digiarea.closure.model.GssRenamingType;
import com.digiarea.closure.model.SoyCodeStyle;

public class CustomBinder {

	public static void bindGssRenamingTypeNone(
			final ObjectProperty<GssRenamingType> renamingTypeProperty,
			final BooleanProperty selectedProperty) {
		if (renamingTypeProperty.get() == GssRenamingType.NONE) {
			selectedProperty.set(true);
		}
		renamingTypeProperty.addListener(new ChangeListener<GssRenamingType>() {
			@Override
			public void changed(
					ObservableValue<? extends GssRenamingType> arg0,
					GssRenamingType arg1, GssRenamingType arg2) {
				if (arg2 == GssRenamingType.NONE) {
					selectedProperty.set(true);
				}
			}
		});
		selectedProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				if (arg2) {
					renamingTypeProperty.set(GssRenamingType.NONE);
				}
			}
		});
	}

	public static void bindGssRenamingTypeDebug(
			final ObjectProperty<GssRenamingType> renamingTypeProperty,
			final BooleanProperty selectedProperty) {
		if (renamingTypeProperty.get() == GssRenamingType.DEBUG) {
			selectedProperty.set(true);
		}
		renamingTypeProperty.addListener(new ChangeListener<GssRenamingType>() {
			@Override
			public void changed(
					ObservableValue<? extends GssRenamingType> arg0,
					GssRenamingType arg1, GssRenamingType arg2) {
				if (arg2 == GssRenamingType.DEBUG) {
					selectedProperty.set(true);
				}
			}
		});
		selectedProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				if (arg2) {
					renamingTypeProperty.set(GssRenamingType.DEBUG);
				}
			}
		});
	}

	public static void bindGssRenamingTypeClosure(
			final ObjectProperty<GssRenamingType> renamingTypeProperty,
			final BooleanProperty selectedProperty) {
		if (renamingTypeProperty.get() == GssRenamingType.CLOSURE) {
			selectedProperty.set(true);
		}
		renamingTypeProperty.addListener(new ChangeListener<GssRenamingType>() {
			@Override
			public void changed(
					ObservableValue<? extends GssRenamingType> arg0,
					GssRenamingType arg1, GssRenamingType arg2) {
				if (arg2 == GssRenamingType.CLOSURE) {
					selectedProperty.set(true);
				}
			}
		});
		selectedProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				if (arg2) {
					renamingTypeProperty.set(GssRenamingType.CLOSURE);
				}
			}
		});

	}

	public static void bindGssOutputFormatCompressed(
			final ObjectProperty<GssOutputFormat> outputFormatProperty,
			final BooleanProperty selectedProperty) {
		if (outputFormatProperty.get() == GssOutputFormat.COMPRESSED) {
			selectedProperty.set(true);
		}
		outputFormatProperty.addListener(new ChangeListener<GssOutputFormat>() {
			@Override
			public void changed(
					ObservableValue<? extends GssOutputFormat> arg0,
					GssOutputFormat arg1, GssOutputFormat arg2) {
				if (arg2 == GssOutputFormat.COMPRESSED) {
					selectedProperty.set(true);
				}
			}
		});
		selectedProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				if (arg2) {
					outputFormatProperty.set(GssOutputFormat.COMPRESSED);
				}
			}
		});

	}

	public static void bindGssOutputFormatPrettyPrint(
			final ObjectProperty<GssOutputFormat> outputFormatProperty,
			final BooleanProperty selectedProperty) {
		if (outputFormatProperty.get() == GssOutputFormat.PRETTY_PRINTED) {
			selectedProperty.set(true);
		}
		outputFormatProperty.addListener(new ChangeListener<GssOutputFormat>() {
			@Override
			public void changed(
					ObservableValue<? extends GssOutputFormat> arg0,
					GssOutputFormat arg1, GssOutputFormat arg2) {
				if (arg2 == GssOutputFormat.PRETTY_PRINTED) {
					selectedProperty.set(true);
				}
			}
		});
		selectedProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				if (arg2) {
					outputFormatProperty.set(GssOutputFormat.PRETTY_PRINTED);
				}
			}
		});

	}

	public static void bindGssOutputFormatDebug(
			final ObjectProperty<GssOutputFormat> outputFormatProperty,
			final BooleanProperty selectedProperty) {
		if (outputFormatProperty.get() == GssOutputFormat.DEBUG) {
			selectedProperty.set(true);
		}
		outputFormatProperty.addListener(new ChangeListener<GssOutputFormat>() {
			@Override
			public void changed(
					ObservableValue<? extends GssOutputFormat> arg0,
					GssOutputFormat arg1, GssOutputFormat arg2) {
				if (arg2 == GssOutputFormat.DEBUG) {
					selectedProperty.set(true);
				}
			}
		});
		selectedProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				if (arg2) {
					outputFormatProperty.set(GssOutputFormat.DEBUG);
				}
			}
		});

	}

	public static void bindSoyCodeStyleBuilder(
			final ObjectProperty<SoyCodeStyle> codeStyleProperty,
			final BooleanProperty selectedProperty) {
		if (codeStyleProperty.get() == SoyCodeStyle.STRINGBUILDER) {
			selectedProperty.set(true);
		}
		codeStyleProperty.addListener(new ChangeListener<SoyCodeStyle>() {
			@Override
			public void changed(ObservableValue<? extends SoyCodeStyle> arg0,
					SoyCodeStyle arg1, SoyCodeStyle arg2) {
				if (arg2 == SoyCodeStyle.STRINGBUILDER) {
					selectedProperty.set(true);
				}
			}
		});
		selectedProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				if (arg2) {
					codeStyleProperty.set(SoyCodeStyle.STRINGBUILDER);
				}
			}
		});
	}

	public static void bindSoyCodeStyleConcatination(
			final ObjectProperty<SoyCodeStyle> codeStyleProperty,
			final BooleanProperty selectedProperty) {
		if (codeStyleProperty.get() == SoyCodeStyle.CONCAT) {
			selectedProperty.set(true);
		}
		codeStyleProperty.addListener(new ChangeListener<SoyCodeStyle>() {
			@Override
			public void changed(ObservableValue<? extends SoyCodeStyle> arg0,
					SoyCodeStyle arg1, SoyCodeStyle arg2) {
				if (arg2 == SoyCodeStyle.CONCAT) {
					selectedProperty.set(true);
				}
			}
		});
		selectedProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				if (arg2) {
					codeStyleProperty.set(SoyCodeStyle.CONCAT);
				}
			}
		});
	}

	public static void bindJSGenerateExportsPath(
			final BooleanProperty generateExportsProperty,
			final BooleanProperty disableProperty) {
		disableProperty.set(!generateExportsProperty.get());
		generateExportsProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				disableProperty.set(!newValue);
			}
		});
	}

	public static void bindJSGenerateExportsButton(
			final BooleanProperty generateExportsProperty,
			final BooleanProperty disableProperty) {
		disableProperty.set(!generateExportsProperty.get());
		generateExportsProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				disableProperty.set(!newValue);
			}
		});
	}

	public static void bindJSInputDelimiterPath(
			final BooleanProperty printInputDelimeterProperty,
			final BooleanProperty disableProperty) {
		disableProperty.set(!printInputDelimeterProperty.get());
		printInputDelimeterProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				disableProperty.set(!newValue);
			}
		});
	}

	public static void bindUnrecognizedFunctionsTable(
			final BooleanProperty allowUnrecognizedFunctionsProperty,
			final BooleanProperty disableProperty) {
		disableProperty.set(allowUnrecognizedFunctionsProperty.get());
		allowUnrecognizedFunctionsProperty
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						disableProperty.set(newValue);
					}
				});
	}

	public static void bindUnrecognizedPropertiesTable(
			final BooleanProperty allowUnrecognizedPropertiesProperty,
			final BooleanProperty disableProperty) {
		disableProperty.set(allowUnrecognizedPropertiesProperty.get());
		allowUnrecognizedPropertiesProperty
				.addListener(new ChangeListener<Boolean>() {
					@Override
					public void changed(
							ObservableValue<? extends Boolean> observable,
							Boolean oldValue, Boolean newValue) {
						disableProperty.set(newValue);
					}
				});
	}

	public static void bindChecksTable(
			final BooleanProperty skipAllPassesProperty,
			final BooleanProperty disableProperty) {
		disableProperty.set(skipAllPassesProperty.get());
		skipAllPassesProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				disableProperty.set(newValue);
			}
		});
	}

	public static void bindOptimizationTable(
			final BooleanProperty functionsOnlyProperty,
			final BooleanProperty disableProperty) {
		disableProperty.set(functionsOnlyProperty.get());
		functionsOnlyProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				disableProperty.set(newValue);
			}
		});
	}

	public static void bindPerformChecks(
			final BooleanProperty skipAllPassesProperty,
			final BooleanProperty selectedProperty) {
		selectedProperty.set(!skipAllPassesProperty.get());
		skipAllPassesProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				selectedProperty.set(!newValue);
			}
		});
	}

	public static void bindBuild(final BooleanProperty buildProperty,
			final BooleanProperty disableProperty) {
		disableProperty.set(!buildProperty.get());
		buildProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				disableProperty.set(!newValue);
			}
		});
	}

	public static void bindBuildMessages(final BooleanProperty buildProperty,
			final BooleanProperty visibleProperty) {
		visibleProperty.set(buildProperty.get());
		buildProperty.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				visibleProperty.set(newValue);
			}
		});
	}

}
