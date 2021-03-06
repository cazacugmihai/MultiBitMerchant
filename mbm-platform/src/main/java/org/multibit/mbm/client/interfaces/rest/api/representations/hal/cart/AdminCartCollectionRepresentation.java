package org.multibit.mbm.client.interfaces.rest.api.representations.hal.cart;

import com.theoryinpractise.halbuilder.DefaultRepresentationFactory;
import com.theoryinpractise.halbuilder.api.Representation;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import org.multibit.mbm.client.common.pagination.PaginatedList;
import org.multibit.mbm.client.domain.model.model.Cart;
import org.multibit.mbm.client.interfaces.rest.api.hal.Representations;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

/**
 * <p>Representation to provide the following to {@link org.multibit.mbm.client.domain.model.model.Cart}:</p>
 * <ul>
 * <li>Creates representation of multiple Carts for an administrator</li>
 * </ul>
 *
 * @since 0.0.1
 */
public class AdminCartCollectionRepresentation {

  private final PublicCartRepresentation publicCartRepresentation = new PublicCartRepresentation();

  public Representation get(PaginatedList<Cart> carts) {
    RepresentationFactory factory = new DefaultRepresentationFactory();

    URI self = UriBuilder.fromPath("/admin/cart").build();
    Representation cartList = Representations.newPaginatedList(self, carts);

    for (Cart cart : carts.list()) {
      Representation cartRepresentation= publicCartRepresentation.get(cart);

      cartRepresentation.withProperty("id", cart.getId())
      // End of build
      ;

      cartList.withRepresentation("/cart/" + cart.getId(), cartRepresentation);
    }

    return cartList;

  }

}
