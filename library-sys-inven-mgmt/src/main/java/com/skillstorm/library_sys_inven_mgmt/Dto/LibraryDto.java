package com.skillstorm.library_sys_inven_mgmt.Dto;

import com.skillstorm.library_sys_inven_mgmt.Model.Library;

public record LibraryDto(
    int id,
    String name,
    String location,
    int maxCap
) {
    public static LibraryDto convertToDto(Library library){
        return new LibraryDto(library.getId(), library.getName(), library.getLocation(), library.getMaxCap());
    }

    public static Library convertToLibrary(LibraryDto libraryDto){
        return new Library(libraryDto.id,libraryDto.name,libraryDto.location,libraryDto.maxCap,null);
    }
}
