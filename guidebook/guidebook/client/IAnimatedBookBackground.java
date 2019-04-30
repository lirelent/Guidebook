package com.lireherz.guidebook.guidebook.client;

public interface IAnimatedBookBackground {
	void startClosing ();

	boolean isFullyOpen ();

	boolean update ();

	void draw (float partialTicks, int bookHeight, float backgroundScale);
}
