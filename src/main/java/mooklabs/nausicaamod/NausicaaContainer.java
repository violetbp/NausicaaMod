/*
 * Forge Mod Loader
 * Copyright (c) 2012-2013 cpw.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser Public License v2.1
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 *
 * Contributors:
 *     cpw - implementation
 */

package mooklabs.nausicaamod;

import java.util.Arrays;

import mooklabs.mookcore.Unused;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModMetadata;

/**
 * @author mooklabs
 *
 */
@Unused(notes= "I dont know if this is acctually being used, but the mod data is working fine") //I think
public class NausicaaContainer extends DummyModContainer
{
    public NausicaaContainer()
    {
        super(new ModMetadata());
        ModMetadata meta = getMetadata();
        meta.modId="nausicaa";
        meta.name="Nausicaa Mod";
        meta.version=Loader.instance().getFMLVersionString();
        meta.credits="Skyapter and others";
        meta.authorList=Arrays.asList("Mookie1097","<--that person is weird");
        meta.description="ITS A MOD :D\nBased on the anime and manga of Nausicaa of the Valley of Wind";
        meta.url="http://nausicaamod.wikia.com/";
        meta.updateUrl="http://nausicaamod.wikia.com/wiki/Downloads/versions/notes";
        meta.screenshots=new String[0];
        meta.logoFile="nausicaaModLogo.png";
    }

    @Override
    public String getGuiClassName()
    {
        return "mooklabs.nausicaamodNausicaaConfigGui";
    }

}