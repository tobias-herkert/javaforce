package jfnetboot;

/** NFolder
 *
 * @author pquiring
 */

import java.util.*;

public class NFolder extends NHandle {
  public ArrayList<NFolder> cfolders = new ArrayList();
  public ArrayList<NFile> cfiles = new ArrayList();
  public boolean converted;  //see FileSystem.doFolderDerived()

  public NFolder() {
  }

  public NFolder(long handle, String local, String path, String name) {
    this.handle = handle;
    this.local = local;
    this.path = path;
    this.name = name;
  }

  private NFolder(String nlocal, NFolder parent, NFolder that) {
    this.parent = parent;
    this.handle = that.handle;
    this.local = nlocal + that.path;
    this.path = that.path;
    this.name = that.name;
    this.symlink = that.symlink;
    FileOps.mkdir(this.local);
    //copy cfolders (clones)
    for(NFolder child : that.cfolders) {
      this.cfolders.add(child.clone(nlocal, this));
    }
    //copy cfiles (clones)
    for(NFile cfile : that.cfiles) {
      NFile clone = cfile.clone();
      if (cfile.rw) {
        if (cfile.symlink == null) {
          FileOps.copyFile(cfile.local, nlocal + cfile.path);
        }
        clone.local = nlocal + clone.path;
      }
      this.cfiles.add(clone);
    }
  }

  public NFolder clone(String nlocal, NFolder parent) {
    return new NFolder(nlocal, parent, this);
  }

  public NFolder clone(String nlocal) {
    return clone(nlocal, null);
  }

  public boolean isFile() {
    return false;
  }

  public boolean isFolder() {
    return true;
  }

  public void close() {}

  public NFolder getFolder(String name) {
    for(int a=0;a<cfolders.size();a++) {
      NFolder folder = cfolders.get(a);
      if (folder.name.equals(name)) {
        return folder;
      }
    }
    return null;
  }

  public NFile getFile(String name) {
    for(int a=0;a<cfiles.size();a++) {
      NFile file = cfiles.get(a);
      if (file.name.equals(name)) {
        return file;
      }
    }
    return null;
  }

  public NHandle getHandle(String name) {
    NFolder folder = getFolder(name);
    if (folder != null) return folder;
    return getFile(name);
  }

  public boolean touch(String newLocalPath) {
    if (rw) return false;
    rw = true;
    local = newLocalPath;
    return true;
  }

  public void add(NHandle node) {
    if (node.isFolder()) {
      cfolders.add((NFolder)node);
    } else {
      cfiles.add((NFile)node);
    }
  }

  public void remove(NHandle node) {
    if (cfolders.contains(node)) {
      cfolders.remove(node);
    } else {
      cfiles.remove(node);
    }
  }

  public String toString() {
    return "NFolder:" + Long.toUnsignedString(handle, 16);
  }
}
