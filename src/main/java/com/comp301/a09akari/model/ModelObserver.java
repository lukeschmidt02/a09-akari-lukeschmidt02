package com.comp301.a09akari.model;

import java.io.FileNotFoundException;

public interface ModelObserver {
  /** When a model value is changed, the model calls update() on all active ModelObserver objects */
  void update(Model model) throws FileNotFoundException;
}
