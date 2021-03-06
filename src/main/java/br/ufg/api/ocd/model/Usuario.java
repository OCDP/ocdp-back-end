/*
 * Copyright (c) 2019. Universidade Federal de Goiás (UFG)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.NivelAtencao;
import br.ufg.api.ocd.enums.StatusUsuario;
import br.ufg.api.ocd.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "usuario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    private String id;
    private String cpf;
    private String nome;
    private String senha;
    private StatusUsuario status;
    private String email;
    private String telefone;
    private NivelAtencao nivelAtencao;
    private TipoUsuario tipoUsuario;
   // private Role role;

}